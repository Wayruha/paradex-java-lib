package trade.wayruha.paradex.util;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.crypto.StarknetCurveSignature;
import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.request.OrderParameters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class OrderSigner {

    //todo similar:chainId, public\privateKey does not change an is boud to config. we can remove them from parameters for simplicity
    public static String sign(OrderParameters orderParameters, String chainId, Felt publicAccAddress, Felt privateAccAddress, long timestamp) {
        // Create the order message
        final String orderMessage = createOrderMessage(chainId, timestamp, orderParameters.getMarket(), orderParameters.getSide(), orderParameters.getType(), orderParameters.getSize(), orderParameters.getPrice());

        final SignatureResult signatureResult = signMessage(orderMessage, publicAccAddress, privateAccAddress);
        return signatureResult.signature;
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

    private static SignatureResult signMessage(String message, Felt accountAddress, Felt privateAddress) {
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

    private static String convertBigIntListToSignatureString(List<BigInteger> list) {
        return list.stream()
                .map(BigInteger::toString)
                .map(s -> "\"" + s + "\"") // Wrap each number in double quotes
                .collect(Collectors.joining(",", "[", "]")); // Keep brackets for JSON format
    }

    private record SignatureResult(String signature, String messageHash) {}
}
