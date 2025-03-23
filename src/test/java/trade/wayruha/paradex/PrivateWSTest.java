package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.websocket.WebSocketClientFactory;

import static trade.wayruha.paradex.TestUtils.*;

public class PrivateWSTest {
    static final TypeReference<OrderDetailsResponse> ORDER_DETAILS_RESPONSE = new TypeReference<>() {
    };
    static final ParadexConfig config = new ParadexConfig(ETH_ADDRESS, PARADEX_ADDRESS, PUBLIC_KEY, PRIVATE_KEY, IS_MAINNET);
    static WebSocketClientFactory factory;
    static final AuthService authService = new AuthService(config);

    @SneakyThrows
    public static void main(String[] args) {
//        testOrderBookUpdate();
        testAccountUpdates();
    }

    private static void testOrderBookUpdate() throws InterruptedException {
        config.setJwtToken(authService.authenticate().getJwtToken());

        factory = new WebSocketClientFactory(config);
        final TestCallback<OrderDetailsResponse> callback = new TestCallback<>(ORDER_DETAILS_RESPONSE);
        factory.userOrderUpdateSubscription(callback);
        Thread.sleep(3_000);
    }

    private static void testAccountUpdates() throws InterruptedException {
        config.setJwtToken(authService.authenticate().getJwtToken());

        factory = new WebSocketClientFactory(config);
        factory.balanceUpdateSubscription(new TestCallback<>(new TypeReference<>() {}, "balance"));
        factory.accountUpdateSubscription(new TestCallback<>(new TypeReference<>() {}, "account"));
        factory.userOrderUpdateSubscription(new TestCallback<>(new TypeReference<>() {}, "orders"));
        factory.positionUpdateSubscription(new TestCallback<>(new TypeReference<>() {}, "positions"));
        Thread.sleep(300_000);
    }
}
