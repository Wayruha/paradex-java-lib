package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import trade.wayruha.paradex.dto.wsresponse.OrderBookUpdate;
import trade.wayruha.paradex.websocket.WebSocketClient;
import trade.wayruha.paradex.websocket.WebSocketClientFactory;

import java.util.Set;

import static trade.wayruha.paradex.TestUtils.PUBLIC_KEY;

public class WebSocketTest {
    static final TypeReference<OrderBookUpdate> ORDER_BOOK_TYPE = new TypeReference<>() {};

    static final ParadexConfig config = new ParadexConfig(PUBLIC_KEY, null);
    static final trade.wayruha.paradex.websocket.WebSocketClientFactory factory = new WebSocketClientFactory(config);

    @SneakyThrows
    public static void main(String[] args) {
        testOrderBookUpdate();
    }

    private static void testOrderBookUpdate() throws InterruptedException {
        final TestCallback<OrderBookUpdate> callback = new TestCallback<>(ORDER_BOOK_TYPE);
        final WebSocketClient<OrderBookUpdate> subscription = factory.orderBookSubscription(Set.of("BTC-USD-PERP", "SOL-USD-PERP"), callback);
        Thread.sleep(5_000);
    }
}
