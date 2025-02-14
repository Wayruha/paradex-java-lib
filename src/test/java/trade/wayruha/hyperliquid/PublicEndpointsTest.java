package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.response.OrderStatusResponse;
import trade.wayruha.paradex.dto.response.PerpAccountSummary;
import trade.wayruha.paradex.service.AccountService;
import trade.wayruha.paradex.service.MetadataService;

public class PublicEndpointsTest {

    public static void main(String[] args) {
        testMarketData();
//        testAccountSummary();
//        testOrderStatus();
    }

    private static void testAccountSummary() {
        final String walletAddress = "";
        final ParadexConfig config = new ParadexConfig(walletAddress, null);
        final AccountService service = new AccountService(config);
        final PerpAccountSummary resp = service.getPerpAccountSummary();
        System.out.println(resp);
    }

    private static void testOrderStatus() {
        final String walletAddress = "";
        final long orderId = Long.parseLong("31226385612");
        final ParadexConfig config = new ParadexConfig(walletAddress, null);
        final AccountService service = new AccountService(config);
        final OrderStatusResponse resp = service.getOrderStatus(orderId);
        System.out.println(resp);
    }

    private static void testMarketData() {
        final ParadexConfig config = new ParadexConfig("", null);
        final MetadataService service = new MetadataService(config);
        service.getMarketData().forEach(System.out::println);
    }
}
