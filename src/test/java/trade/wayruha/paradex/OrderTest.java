package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.OrderFlag;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.SelfTradePrevention;
import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.request.OrderInstruction;
import trade.wayruha.paradex.dto.request.OrderParameters;
import trade.wayruha.paradex.dto.response.AllOpenOrdersResponse;
import trade.wayruha.paradex.dto.response.AllPositionsResponse;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.service.OrderService;
import trade.wayruha.paradex.util.AuthRequestBuilder;

import java.math.BigDecimal;
import java.util.List;

public class OrderTest {
    static final String PUBLIC_KEY = "";
    static final String PRIVATE_KEY = "";

    private static OrderService orderService;
    private static AuthService authService;
    private static final String CANCEL_ORDER_ID = "1739663638290201703994530000";

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig();
        config.setPublicKey(PUBLIC_KEY);
        config.setPrivateKey(PRIVATE_KEY);
        authService = new AuthService(config);

        final Long hours24 = (long) (24 * 60 * 60);

        AuthRequest authRequest = AuthRequestBuilder.buildRequest(config.getPublicKey(), config.getPrivateKey(), config.getChainId(), hours24);
        System.out.println(authRequest);
        AuthResponse response = authService.authenticate(authRequest);
        config.setJwtToken(response.getJwtToken());
        System.out.println(config.getJwtToken());

        orderService = new OrderService(config);

//        testGetAllOpenOrders();
        testCreateOrder();
//        testCancelOrderById(CANCEL_ORDER_ID);
//        testGetOrderDetailsByOrderId("1739635538240201703942180000");
//        testGetAllPositions();
    }

    private static void testGetAllOpenOrders() {
        AllOpenOrdersResponse allOpenOrders = orderService.getAllOpenOrders();
        allOpenOrders.getOrders().forEach(System.out::println);
    }

    private static void testCancelOrderById(String orderId) {
        orderService.cancelOrderById(orderId);
    }

    private static void testCreateOrder() {
        OrderParameters orderParameters = new OrderParameters(
                OrderInstruction.Gtc,
                "BTC-USD-PERP",
                null,
                OrderSide.Buy,
                OrderType.Market,
                new BigDecimal("1"),
                List.of(OrderFlag.REDUCE_ONLY),
                SelfTradePrevention.ExpireTaker,
                null);
        System.out.println(orderService.createOrder(orderParameters));
    }

    private static void testGetOrderDetailsByOrderId(String orderId) {
        OrderDetailsResponse orderDetails = orderService.getOrderDetailsByOrderId(orderId);
        System.out.println(orderDetails);
    }

    private static void testGetAllPositions() {
        AllPositionsResponse allPositions = orderService.getAllPositions();
        allPositions.getOrders().forEach(System.out::println);
    }
}
