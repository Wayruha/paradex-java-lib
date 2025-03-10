package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.*;
import trade.wayruha.paradex.dto.request.OrderHistoryQueryParams;
import trade.wayruha.paradex.dto.request.OrderParameters;
import trade.wayruha.paradex.dto.response.*;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.service.OrderService;

import java.math.BigDecimal;
import java.util.List;


public class OrderTest {

    private static OrderService orderService;
    private static AuthService authService;
    private static final String CANCEL_ORDER_ID = "1739663638290201703994530000";
    private static final String CANCEL_CLIENT_ORDER_ID = "1741023334337";

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig(true);
        config.setPublicKey(TestUtils.PUBLIC_KEY);
        config.setPrivateKey(TestUtils.PRIVATE_KEY);
        authService = new AuthService(config);

        AuthResponse response = authService.authenticate();
        config.setJwtToken(response.getJwtToken());
        System.out.println(config.getJwtToken());

        orderService = new OrderService(config);

        testGetAllOpenOrders();
        testCreateMarketOrder();
        testCreateLimitOrder();
        testCancelOrderById(CANCEL_ORDER_ID);
        testCancelOrderByClientId(CANCEL_CLIENT_ORDER_ID);
        testGetOrderDetailsByOrderId("1739635538240201703942180000");
        testGetAllPositions();
        testGetOrdersHistory();
        testGetOrderByClientOrderId("123");
    }

    private static void testGetAllOpenOrders() {
        AllOpenOrdersResponse allOpenOrders = orderService.getAllOpenOrders();
        allOpenOrders.getOrders().forEach(System.out::println);
    }

    private static void testCancelOrderById(String orderId) {
        orderService.cancelOrderById(orderId);
    }

    private static void testCancelOrderByClientId(String clientId) {
        orderService.cancelOrderByClientId(clientId);
    }

    private static void testCreateMarketOrder() {
        OrderParameters orderParameters = new OrderParameters(
                TimeInForce.GTC,
                "ETH-USD-PERP",
                null,
                OrderSide.BUY,
                OrderType.MARKET,
                new BigDecimal("1"),
                "123",
                List.of(OrderFlag.REDUCE_ONLY),
                SelfTradePrevention.EXPIRE_TAKER,
                null);
        System.out.println(orderService.placeOrder(orderParameters));
    }

    private static void testCreateLimitOrder() {
        OrderParameters orderParameters = new OrderParameters(
                TimeInForce.GTC,
                "ETH-USD-PERP",
                new BigDecimal("100000"),
                OrderSide.SELL,
                OrderType.LIMIT,
                new BigDecimal("1"),
                "123",
                List.of(OrderFlag.REDUCE_ONLY),
                SelfTradePrevention.EXPIRE_TAKER,
                null);
        System.out.println(orderService.placeOrder(orderParameters));
    }

    private static void testGetOrderDetailsByOrderId(String orderId) {
        OrderDetailsResponse orderDetails = orderService.getOrderDetailsByOrderId(orderId);
        System.out.println(orderDetails);
    }

    private static void testGetAllPositions() {
        AllPositionsResponse allPositions = orderService.getAllPositions();
        allPositions.getPositions().forEach(System.out::println);
    }

    private static void testGetOrdersHistory() {
        final OrderHistoryQueryParams params = new OrderHistoryQueryParams(
                null,
                null,
                null,
                null,
                null,
                OrderSide.BUY,
                null,
                OrderStatus.CLOSED,
                OrderType.MARKET
        );
        OrderHistoryResponse ordersHistory = orderService.getOrdersHistory(params);
        ordersHistory.getOrders().forEach(System.out::println);
    }

    private static void testGetOrderByClientOrderId(String clientOrderId) {
        System.out.println(orderService.getOrderByClientOrderId(clientOrderId));
    }
}
