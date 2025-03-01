package trade.wayruha.paradex.service;

import com.swmansion.starknet.data.types.Felt;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.request.OrderCreateRequest;
import trade.wayruha.paradex.dto.request.OrderHistoryQueryParams;
import trade.wayruha.paradex.dto.request.OrderParameters;
import trade.wayruha.paradex.dto.response.AllOpenOrdersResponse;
import trade.wayruha.paradex.dto.response.AllPositionsResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.dto.response.OrderHistoryResponse;
import trade.wayruha.paradex.service.endpoint.OrderEndpoints;
import trade.wayruha.paradex.util.OrderSigner;

public class OrderService extends ServiceBase {
    private final OrderEndpoints orderApi;
    private final OrderSigner orderSigner;

    public OrderService(ParadexConfig config) {
        super(config);
        this.orderApi = createService(OrderEndpoints.class);
        final Felt privateAddress = Felt.fromHex(config.getPrivateKey());
        final Felt accountAddress = Felt.fromHex(config.getPublicKey());
        this.orderSigner = new OrderSigner(config.getChainId(), accountAddress, privateAddress);
    }

    public AllOpenOrdersResponse getAllOpenOrders() {
        return client.executeSync(orderApi.getAllOpenOrders());
    }

    public OrderDetailsResponse getOrderDetailsByOrderId(String orderId) {
        return client.executeSync(orderApi.getOrderDetailsById(orderId));
    }

    public AllPositionsResponse getAllPositions() {
        return client.executeSync(orderApi.getAllPositions());
    }

    public void cancelOrderById(String orderId) {
        client.executeSync(orderApi.cancelOrderById(orderId));
    }

    public OrderDetailsResponse placeOrder(OrderParameters orderParameters) {
        final OrderCreateRequest orderCreateRequest = buildOrder(orderParameters);
        return client.executeSync(orderApi.createOrder(orderCreateRequest));
    }

    public OrderHistoryResponse getOrdersHistory(OrderHistoryQueryParams params) {
        return client.executeSync(orderApi.getOrdersHistory(params.toQueryMap()));
    }

    public OrderCreateRequest buildOrder(OrderParameters orderParameters) {
        final long timestamp = System.currentTimeMillis();
        final String signature = orderSigner.sign(orderParameters, timestamp);
        return new OrderCreateRequest(orderParameters, signature, timestamp, 0);
    }

    /**
     * @return only active order
     * @apiNote if there are multiple orders with the same client_id, will return the first order with that id
     */
    public OrderDetailsResponse getOrderByClientOrderId(String clientOrderId) {
        return client.executeSync(orderApi.getActiveOrderByClientOrderId(clientOrderId));
    }
}
