package trade.wayruha.paradex.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.config.ApiClient;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.dto.wsrequest.Auth;
import trade.wayruha.paradex.dto.wsrequest.WSRequest;
import trade.wayruha.paradex.dto.wsresponse.OrderBookUpdate;
import trade.wayruha.paradex.dto.wsrequest.Subscription;
import trade.wayruha.paradex.service.AuthService;

import java.util.*;
import java.util.stream.Collectors;

public class WebSocketClientFactory {
    private final ParadexConfig config;
    private final ApiClient apiClient;
    @Setter
    private ObjectMapper objectMapper;
    private AuthService authService;

    public WebSocketClientFactory(ParadexConfig config) {
        this.apiClient = new ApiClient(config);
        this.config = config;
        this.objectMapper = config.getObjectMapper();
        this.authService = new AuthService(config);
    }

    //public subscriptions
    public WebSocketClient<OrderBookUpdate> orderBookSubscription(Collection<String> symbols, WebSocketCallback<OrderBookUpdate> callback) {
        final List<WSRequest> channels = symbols.stream()
                .map(WebSocketClientFactory::buildOrderBookSubscription).collect(Collectors.toList());
        final WebSocketClient<OrderBookUpdate> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }

    //private subscriptions
    //todo to be tested
    public WebSocketClient<OrderDetailsResponse> userOrderUpdateSubscription(WebSocketCallback<OrderDetailsResponse> callback) {
        Objects.requireNonNull(config.getJwtToken(), "jwt token is required for userOrderUpdateSubscription");
        final List<WSRequest> channels = new ArrayList<>();
        channels.add(new Auth(config.getJwtToken()));
        channels.add(new Subscription("orders.ALL"));
        final WebSocketClient<OrderDetailsResponse> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }

    private static Subscription buildOrderBookSubscription(String marketSymbol){
        final String subscriptionStr = String.format("order_book.%s.snapshot@15@100ms", marketSymbol);
        return new Subscription(subscriptionStr);
    }
}
