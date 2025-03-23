package trade.wayruha.paradex.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.dto.wsrequest.Auth;
import trade.wayruha.paradex.dto.wsrequest.Subscription;
import trade.wayruha.paradex.dto.wsrequest.WSRequest;
import trade.wayruha.paradex.dto.wsresponse.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WebSocketClientFactory {
    private final ParadexConfig config;
    @Setter
    private ObjectMapper objectMapper;

    public WebSocketClientFactory(ParadexConfig config) {
        this.config = config;
        this.objectMapper = config.getObjectMapper();
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
    public WebSocketClient<OrderDetailsResponse> userOrderUpdateSubscription(WebSocketCallback<OrderDetailsResponse> callback) {
        Objects.requireNonNull(config.getJwtToken(), "jwt token is required for userOrderUpdateSubscription");
        final List<WSRequest> channels = new ArrayList<>();
        channels.add(new Auth(config.getJwtToken()));
        channels.add(new Subscription("orders.ALL"));
        final WebSocketClient<OrderDetailsResponse> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }

    public WebSocketClient<AccountDetails> accountUpdateSubscription(WebSocketCallback<AccountDetails> callback) {
        Objects.requireNonNull(config.getJwtToken(), "jwt token is required for userOrderUpdateSubscription");
        final List<WSRequest> channels = new ArrayList<>();
        channels.add(new Auth(config.getJwtToken()));
        channels.add(new Subscription("account"));
        final WebSocketClient<AccountDetails> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }

    public WebSocketClient<BalanceUpdate> balanceUpdateSubscription(WebSocketCallback<BalanceUpdate> callback) {
        Objects.requireNonNull(config.getJwtToken(), "jwt token is required for userOrderUpdateSubscription");
        final List<WSRequest> channels = new ArrayList<>();
        channels.add(new Auth(config.getJwtToken()));
        channels.add(new Subscription("balance_events"));
        final WebSocketClient<BalanceUpdate> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }

    public WebSocketClient<PositionUpdate> positionUpdateSubscription(WebSocketCallback<PositionUpdate> callback) {
        Objects.requireNonNull(config.getJwtToken(), "jwt token is required for userOrderUpdateSubscription");
        final List<WSRequest> channels = new ArrayList<>();
        channels.add(new Auth(config.getJwtToken()));
        channels.add(new Subscription("positions"));
        final WebSocketClient<PositionUpdate> client = new WebSocketClient<>(config, objectMapper, callback);
        client.connect(channels);
        return client;
    }


    private static Subscription buildOrderBookSubscription(String marketSymbol) {
        final String subscriptionStr = String.format("order_book.%s.snapshot@15@100ms", marketSymbol);
        return new Subscription(subscriptionStr);
    }
}
