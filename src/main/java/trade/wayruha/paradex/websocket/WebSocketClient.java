package trade.wayruha.paradex.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.*;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.config.Constant;
import trade.wayruha.paradex.dto.wsrequest.Subscription;
import trade.wayruha.paradex.dto.wsrequest.WSRequest;
import trade.wayruha.paradex.exception.WSException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;


@Slf4j
public class WebSocketClient<T> {
    private static final int INVALID_SUBSCRIBE_ERROR_CODE = -32600;

    protected static long WEB_SOCKET_RECONNECTION_DELAY_MS = 10_000;
    protected final ParadexConfig config;
    protected final ObjectMapper objectMapper;
    protected final WebSocketCallback<T> callback;
    @Getter
    protected final int id;
    protected final String logPrefix;
    protected final WebSocketFactory wsFactory;
    protected final AtomicInteger reconnectionCounter;
    protected Map<Integer, WSRequest> subscriptions;
    protected WebSocket webSocket;
    @Getter
    protected long lastReceivedTime;

    @SneakyThrows
    public WebSocketClient(ParadexConfig config, ObjectMapper mapper, WebSocketCallback<T> callback) {
        this.config = config;
        this.callback = callback;
        this.objectMapper = mapper;
        this.subscriptions = new HashMap<>();
        this.reconnectionCounter = new AtomicInteger(0);
        this.id = IdGenerator.getNextId();
        this.logPrefix = "[ws-" + this.id + "]";
        this.wsFactory = getWebSocketFactory();
    }

    @SneakyThrows
    public void connect(Collection<WSRequest> subscriptions) {
        try {
            log.debug("{} Connecting to: {}", logPrefix, subscriptions);
            initializeConnection();
            if (subscriptions != null) {
                this.subscriptions.clear();
                subscribe(subscriptions);
            }
        } catch (OpeningHandshakeException ex) {
            logOpeningException(ex);
            throw ex;
        }
    }

    protected void initializeConnection() throws IOException, WebSocketException {
        this.webSocket = wsFactory
                .createSocket(config.getWebSocketHost())
                .setMissingCloseFrameAllowed(true)
                .setPingSenderName("PING_SENDER")
                .addListener(new AdapterImplementation());
        this.webSocket.connect();
    }

    public void subscribe(Collection<WSRequest> subscriptions) {
        subscriptions.forEach(this::subscribe);
    }

    @SneakyThrows
    public void subscribe(WSRequest subscription) {
        if (this.sendRequest(subscription)) {
            this.subscriptions.put(subscription.getId(), subscription);
        }
    }

    public boolean sendRequest(WSRequest request) {
        try {
            final String requestStr = objectMapper.writeValueAsString(request);
            if (!(request instanceof Subscription)) {
                log.debug("{} sending msg({}) {}", logPrefix, request.getId(), requestStr);
            }
            webSocket.sendText(requestStr);
        } catch (Exception e) {
            log.error("{} Failed to send message: {}. Closing the connection...", logPrefix, request);
            handleFailure(e);
            return false;
        }
        return true;
    }

    public void close() {
        log.info("{} Closing WS.", logPrefix);
        if (webSocket != null) {
            webSocket.disconnect();
            webSocket = null;
        }
        this.subscriptions.clear();
    }

    @SneakyThrows
    public boolean reConnect() {
        boolean success = false;
        while (!success && (config.isWebSocketReconnectAlways() || reconnectionCounter.incrementAndGet() < config.getWebSocketMaxReconnectAttempts())) {
            try {
                log.debug("{} Try to reconnect. Attempt #{}", logPrefix, reconnectionCounter.get());
                close();
                connect(this.subscriptions.values());
                success = true;
            } catch (Exception e) {
                log.error("{} [Connection error] Error while reconnecting: {}", logPrefix, e.getMessage(), e);
                Thread.sleep(WEB_SOCKET_RECONNECTION_DELAY_MS);
            }
            log.info("{} Successfully reconnected to WebSocket channels: {}.", logPrefix, this.subscriptions);
        }
        return success;
    }

    private void handleMessage(String message) {
        lastReceivedTime = System.currentTimeMillis();
        log.trace("{} onMessage WS event: {}", logPrefix, message);
        try {
            T data = parseResponseBody(message);
            if (data != null) callback.onResponse(data);
        } catch (WSException ex) {
            if (ex.getPayload().getCode() == INVALID_SUBSCRIBE_ERROR_CODE) {
                final WSRequest request = subscriptions.get(ex.getRequestId());
                log.error("{} WS subscription failed {}: {}", logPrefix, request, ex.getMessage());
            } else {
                log.error("{} WS message parsing failed. Closing it. Response: {}", log, message, ex);
                close();
            }
        } catch (Exception e) {
            log.error("{} WS message parsing failed. Closing it. Response: {}", log, message, e);
            close();
        }
    }

    private void handleFailure(Throwable ex) {
        if (!reConnect()) {
            log.warn("{} [Connection error] Connection will be closed due to error: {}", logPrefix,
                    ex != null ? ex.getMessage() : Constant.WEBSOCKET_INTERRUPTED_EXCEPTION);
            close();
            callback.onFailure(ex, null);
        }
    }

    private T parseResponseBody(String message) throws IOException {
        final ObjectNode response = objectMapper.readValue(message, ObjectNode.class);
        final JsonNode errorNode = response.get("error");
        if (errorNode != null) {
            final WSException.Payload payload = objectMapper.convertValue(errorNode, WSException.Payload.class);
            throw new WSException(response.get("id").asInt(), errorNode.get("code").asInt(), payload);
        }
        if (ofNullable(response.get("method"))
                .map(JsonNode::asText)
                .filter(m -> m.equalsIgnoreCase("subscription"))
                .isPresent()) {
            final JsonNode dataNode = response.get("params").get("data");
            return objectMapper.convertValue(dataNode, callback.getType());
        }
        return null; //todo most likely it does not cover all cases: should be implemented
    }

    private void logOpeningException(OpeningHandshakeException e) {
        final StatusLine sl = e.getStatusLine();
        String message = String.format("=== Status Line ===|HTTP Version  = %s|Status Code   = %d|Reason Phrase = %s|",
                sl.getHttpVersion(), sl.getStatusCode(), sl.getReasonPhrase());

        // HTTP headers.
        Map<String, List<String>> headers = e.getHeaders();
        message += "||=== HTTP Headers ===|";
        final String headersStr = headers.entrySet().stream()
                .map(entry -> entry.getValue() == null ? entry.getKey() : String.format("%s: %s", entry.getKey(), String.join(".", entry.getValue())))
                .collect(Collectors.joining("|"));
        message += headersStr;
        log.error("{} Opening exception: {}. Message: {}", logPrefix, message, e.getMessage());
    }

    private static WebSocketFactory getWebSocketFactory() {
        final WebSocketFactory factory = new WebSocketFactory()
                .setConnectionTimeout(5_000);
        return factory;
    }

    private class AdapterImplementation extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String message) {
            handleMessage(message);
        }

        @Override
        public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onFrameSent(websocket, frame);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
            log.debug("{} onOpen WS event.", logPrefix);
            lastReceivedTime = System.currentTimeMillis();
            callback.onOpen(null);
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
            log.debug("{} onDisconnected: Closed by server: {}.", logPrefix, closedByServer);
            final WebSocketFrame frame = serverCloseFrame != null ? serverCloseFrame : clientCloseFrame;
            final int code = frame != null ? frame.getCloseCode() : -1;
            final String reason = frame != null ? frame.getCloseReason() : "Unknown";
            callback.onClosed(code, reason);
        }

        @Override
        public void onError(WebSocket websocket, WebSocketException cause) {
            log.warn("{} onError: Closed by server: {}.", logPrefix, cause.getMessage());
            handleFailure(cause);
        }

        @Override
        public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onPingFrame(websocket, frame);
            lastReceivedTime = System.currentTimeMillis();
        }

        @Override
        public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
            super.onPongFrame(websocket, frame);
            lastReceivedTime = System.currentTimeMillis();
        }
    }
}