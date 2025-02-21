package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.websocket.WebSocketClient;
import trade.wayruha.paradex.websocket.WebSocketClientFactory;

//TODO fix: it says invalid token
public class PrivateWSTest {
    static final TypeReference<OrderDetailsResponse> ORDER_DETAILS_RESPONSE = new TypeReference<>() {
    };
    static final ParadexConfig config = new ParadexConfig(TestUtils.PUBLIC_KEY, TestUtils.PRIVATE_KEY);
    static final WebSocketClientFactory factory = new WebSocketClientFactory(config);

    @SneakyThrows
    public static void main(String[] args) {
        testOrderBookUpdate();
    }

    private static void testOrderBookUpdate() throws InterruptedException {
        final TestCallback<OrderDetailsResponse> callback = new TestCallback<>(ORDER_DETAILS_RESPONSE);
        final WebSocketClient<OrderDetailsResponse> subscription = factory.userOrderUpdateSubscription(callback);
        Thread.sleep(100_000);
    }
}
