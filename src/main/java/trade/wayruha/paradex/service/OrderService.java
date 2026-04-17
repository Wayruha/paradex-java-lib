package trade.wayruha.paradex.service;

import com.swmansion.starknet.data.types.Felt;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.request.OrderCreateRequest;
import trade.wayruha.paradex.dto.request.OrderHistoryQueryParams;
import trade.wayruha.paradex.dto.request.OrderParameters;
import trade.wayruha.paradex.dto.response.AllOpenOrdersResponse;
import trade.wayruha.paradex.dto.response.AllPositionsResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.dto.response.OrderHistoryResponse;
import trade.wayruha.paradex.service.endpoint.OrderEndpoints;
import trade.wayruha.paradex.util.SignatureResult;
import trade.wayruha.paradex.util.SigningUtil;

import java.math.BigDecimal;

public class OrderService extends ServiceBase {
    private final OrderEndpoints orderApi;

    public OrderService(ParadexConfig config) {
        super(config);
        this.orderApi = createService(OrderEndpoints.class);
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

    public void cancelOrderByClientId(String clientId) {
        client.executeSync(orderApi.cancelOrderByClientId(clientId));
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
        final String orderMessage = createOrderMessage(getConfig().getChainId(), timestamp, orderParameters.getMarket(),
                orderParameters.getSide(), orderParameters.getType(), orderParameters.getSize(), orderParameters.getPrice());
        final SignatureResult signature = SigningUtil.signMessage(orderMessage, getConfig().getParadexAddress(), getConfig().getStarknetPrivateKey());
        return new OrderCreateRequest(orderParameters, signature.signature(), timestamp, 60_000);
    }

    /**
     * @return only active order
     * @apiNote if there are multiple orders with the same client_id, will return the first order with that id
     */
    public OrderDetailsResponse getOrderByClientOrderId(String clientOrderId) {
        return client.executeSync(orderApi.getActiveOrderByClientOrderId(clientOrderId));
    }

    /**
     * @param chainId in full format e.g. PRIVATE_SN_POTC_SEPOLIA
     */
    private static String createOrderMessage(String chainId, long timestamp, String market, OrderSide side, OrderType orderType, BigDecimal size, BigDecimal price) {
        int chainSide = (side == OrderSide.BUY) ? 1 : 2;
        BigDecimal chainPrice = (orderType == OrderType.MARKET) ? BigDecimal.ZERO : price.scaleByPowerOfTen(8);
        BigDecimal chainSize = size.scaleByPowerOfTen(8);
        String chainIdHex = Felt.fromShortString(chainId).hexString();

        return String.format(
                """
                        {
                            "message": {
                                "timestamp": %d,
                                "market": "%s",
                                "side": %d,
                                "orderType": "%s",
                                "size": %s,
                                "price": %s
                            },
                            "domain": {"name": "Paradex", "chainId": "%s", "version": "1"},
                            "primaryType": "Order",
                            "types": {
                                "StarkNetDomain": [
                                    {"name": "name", "type": "felt"},
                                    {"name": "chainId", "type": "felt"},
                                    {"name": "version", "type": "felt"}
                                ],
                                "Order": [
                                    {"name": "timestamp", "type": "felt"},
                                    {"name": "market", "type": "felt"},
                                    {"name": "side", "type": "felt"},
                                    {"name": "orderType", "type": "felt"},
                                    {"name": "size", "type": "felt"},
                                    {"name": "price", "type": "felt"}
                                ]
                            }
                        }
                        """,
                timestamp, market, chainSide, orderType.getName(), chainSize.toPlainString(), chainPrice.toPlainString(), chainIdHex
        );
    }
}
