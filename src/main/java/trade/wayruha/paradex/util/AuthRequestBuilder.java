package trade.wayruha.paradex.util;

import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.signer.StarkCurveSigner;
import lombok.RequiredArgsConstructor;
import trade.wayruha.paradex.dto.request.AuthRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthRequestBuilder {

    private final String privateKey;
    private final String publicKey;
    private final String chainId;

    /**
     * Builds AuthRequest previously creating authentication signature
     * @param expiryDurationSeconds lifetime of returned JWT token in seconds
     */
    public AuthRequest buildRequest(Long expiryDurationSeconds) {
        final Felt accountAddress = Felt.fromHex(publicKey);
        final Felt privateKeyFelt = Felt.fromHex(privateKey);

        // Get current timestamp in seconds
        final long timestamp = System.currentTimeMillis() / 1000;
        final long expiry = timestamp + expiryDurationSeconds; // now + timeToExpireMs

        final String chainIdHex = Felt.fromShortString(chainId).hexString();

        // Create the auth message
        final String authMessage = createAuthMessage(timestamp, expiry, chainIdHex);

        // Convert the auth message to a TypedData object
        final TypedData typedData = TypedData.fromJsonString(authMessage);

        // Create new StarkCurveSigner with the private key
        final StarkCurveSigner scSigner = new StarkCurveSigner(privateKeyFelt);

        // Sign the typed data
        final List<Felt> signature = scSigner.signTypedData(typedData, accountAddress);

        // Convert the signature to a string
        final List<BigInteger> signatureBigInt = signature.stream()
                .map(Felt::getValue)
                .collect(Collectors.toList());
        final String signatureStr = convertBigIntListToSignatureString(signatureBigInt);

        return new AuthRequest(publicKey, signatureStr, Long.toString(timestamp), Long.toString(expiry));
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
