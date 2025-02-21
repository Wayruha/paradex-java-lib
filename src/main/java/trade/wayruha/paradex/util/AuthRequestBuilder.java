package trade.wayruha.paradex.util;

import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.signer.StarkCurveSigner;
import trade.wayruha.paradex.dto.request.AuthRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class AuthRequestBuilder {

    //todo does it make sense to build it from Config, given that publicKey, private and chainId are static
    // and therefore some operations could be pre-computed and stored as a field?
    /**
     * Builds AuthRequest previously creating authentication signature
     * @param chainId special id of paradox project e.g. mainnet or testnet. Can be found at /system-config endpoint of project host
     * @param expiryDurationSeconds lifetime of returned JWT token in seconds
     */
    public static AuthRequest buildRequest(String publicKeyStr, String privateKeyStr, String chainId, Long expiryDurationSeconds) {
        final Felt accountAddress = Felt.fromHex(publicKeyStr);
        final Felt privateKey = Felt.fromHex(privateKeyStr);

        // Get current timestamp in seconds
        final long timestamp = System.currentTimeMillis() / 1000;
        final long expiry = timestamp + expiryDurationSeconds; // now + timeToExpireMs

        final String chainIdHex = Felt.fromShortString(chainId).hexString();

        // Create the auth message
        final String authMessage = createAuthMessage(timestamp, expiry, chainIdHex);

        // Convert the auth message to a TypedData object
        final TypedData typedData = TypedData.fromJsonString(authMessage);

        // Create new StarkCurveSigner with the private key
        final StarkCurveSigner scSigner = new StarkCurveSigner(privateKey);

        // Sign the typed data
        final List<Felt> signature = scSigner.signTypedData(typedData, accountAddress);

        // Convert the signature to a string
        final List<BigInteger> signatureBigInt = signature.stream()
                .map(Felt::getValue)
                .collect(Collectors.toList());
        final String signatureStr = convertBigIntListToSignatureString(signatureBigInt);

        return new AuthRequest(publicKeyStr, signatureStr, Long.toString(timestamp), Long.toString(expiry));
    }

    private static String convertBigIntListToSignatureString(List<BigInteger> list) {
        return list.stream()
                .map(BigInteger::toString)
                .map(s -> "\"" + s + "\"") // Wrap each number in double quotes
                .collect(Collectors.joining(",", "[", "]")); // Keep brackets for a tuple-like format
    }

    private static String createAuthMessage(long timestamp, long expiration, String chainIdHex) {
        return String.format(
                "{\"message\": {\"method\": \"POST\", \"path\": \"/v1/auth\", \"body\": \"\", " +
                        "\"timestamp\": %d, \"expiration\": %d}, " +
                        "\"domain\": {\"name\": \"Paradex\", \"chainId\": \"%s\", \"version\": \"1\"}, " +
                        "\"primaryType\": \"Request\", " +
                        "\"types\": {\"StarkNetDomain\": [{\"name\": \"name\", \"type\": \"felt\"}, " +
                        "{\"name\": \"chainId\", \"type\": \"felt\"}, {\"name\": \"version\", \"type\": \"felt\"}], " +
                        "\"Request\": [{\"name\": \"method\", \"type\": \"felt\"}, " +
                        "{\"name\": \"path\", \"type\": \"felt\"}, {\"name\": \"body\", \"type\": \"felt\"}, " +
                        "{\"name\": \"timestamp\", \"type\": \"felt\"}, {\"name\": \"expiration\", \"type\": \"felt\"}]}}",
                timestamp, expiration, chainIdHex);
    }
}
