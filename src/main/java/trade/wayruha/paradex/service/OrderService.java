package trade.wayruha.paradex.service;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.crypto.StarknetCurveSignature;
import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.request.OrderCreateRequest;
import trade.wayruha.paradex.dto.request.OrderParameters;
import trade.wayruha.paradex.dto.response.AllOpenOrdersResponse;
import trade.wayruha.paradex.dto.response.AllPositionsResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;
import trade.wayruha.paradex.service.endpoint.OrderEndpoints;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService extends ServiceBase{
    private final OrderEndpoints orderApi;
    private final String chainId;
    private final Felt accountAddress;
    private final Felt privateAddress;

    public OrderService(ParadexConfig config) {
        super(config);
        this.orderApi = createService(OrderEndpoints.class);
        this.chainId = config.getChainId();
        this.privateAddress = Felt.fromHex(config.getPrivateKey());
        this.accountAddress = Felt.fromHex(config.getPublicKey());
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

    public void cancelOrderById(String orderId) { client.executeSync(orderApi.cancelOrderById(orderId)); }

    public OrderDetailsResponse createOrder(OrderParameters orderParameters) {
        final OrderCreateRequest orderCreateRequest = buildOrder(orderParameters);
        return client.executeSync(orderApi.createOrder(orderCreateRequest));
    }

    public OrderCreateRequest buildOrder(OrderParameters orderParameters) {
        final long timestamp = System.currentTimeMillis();

        // Create the order message
        final String orderMessage = createOrderMessage(chainId, timestamp, orderParameters.getMarket(), orderParameters.getSide(), orderParameters.getType(), orderParameters.getSize(), orderParameters.getPrice());

        System.out.println(orderMessage);

        final SignatureResult signatureResult = getSignature(orderMessage);

        return new OrderCreateRequest(orderParameters, signatureResult.signature(), timestamp, 0);
    }

    private SignatureResult getSignature(String message) {
        final TypedData typedData = TypedData.fromJsonString(message);
        final Felt messageHash = typedData.getMessageHash(accountAddress);
        final String messageHashStr = messageHash.hexString();

        StarknetCurveSignature signature = StarknetCurve.sign(privateAddress, messageHash);
        List<BigInteger> signatureBigInt = signature.toList().stream()
                .map(Felt::getValue)
                .collect(Collectors.toList());

        String signatureStr = convertBigIntListToSignatureString(signatureBigInt);
        return new SignatureResult(signatureStr, messageHashStr);
    }

    /**
     * @param chainId in full format e.g. PRIVATE_SN_POTC_SEPOLIA
     */
    private static String createOrderMessage(String chainId, long timestamp, String market, OrderSide side, OrderType orderType, BigDecimal size, BigDecimal price) {
        int chainSide = (side == OrderSide.Buy) ? 1 : 2;
        BigDecimal chainPrice = (orderType == OrderType.Market) ? BigDecimal.ZERO : price.scaleByPowerOfTen(8);
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

    private static String convertBigIntListToSignatureString(List<BigInteger> list) {
        return list.stream()
                .map(BigInteger::toString)
                .map(s -> "\"" + s + "\"") // Wrap each number in double quotes
                .collect(Collectors.joining(",", "[", "]")); // Keep brackets for JSON format
    }

    private record SignatureResult(String signature, String messageHash) {}
}
