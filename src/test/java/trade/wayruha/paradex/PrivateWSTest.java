package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.websocket.WebSocketClient;
import trade.wayruha.paradex.websocket.WebSocketClientFactory;

public class PrivateWSTest {
    static final TypeReference<OrderDetailsResponse> ORDER_DETAILS_RESPONSE = new TypeReference<>() {
    };
    static final ParadexConfig config = new ParadexConfig(TestUtils.PUBLIC_KEY, TestUtils.PRIVATE_KEY);
    static WebSocketClientFactory factory;
    static final AuthService authService = new AuthService(config);

    @SneakyThrows
    public static void main(String[] args) {
        testOrderBookUpdate();
    }

    private static void testOrderBookUpdate() throws InterruptedException {
        config.setPublicKey(TestUtils.PUBLIC_KEY);
        config.setPrivateKey(TestUtils.PRIVATE_KEY);
        config.setJwtToken(authService.authenticate().getJwtToken());

        factory = new WebSocketClientFactory(config);
        final TestCallback<OrderDetailsResponse> callback = new TestCallback<>(ORDER_DETAILS_RESPONSE);
        final WebSocketClient<OrderDetailsResponse> subscription = factory.userOrderUpdateSubscription(callback);
        Thread.sleep(100_000);
    }
}
