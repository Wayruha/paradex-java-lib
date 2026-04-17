package trade.wayruha.paradex.util;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.crypto.StarknetCurveSignature;
import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.signer.StarkCurveSigner;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import trade.wayruha.paradex.dto.request.AuthRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SigningUtil {

    /**
     * Builds AuthRequest previously creating authentication signature
     * @param expiryDurationSeconds lifetime of returned JWT token in seconds
     */
    public static AuthRequest buildRequest(String paradexAddress, String starknetPrivateKey, String chainId, Long expiryDurationSeconds) {
        // Get current timestamp in seconds
        final long timestamp = System.currentTimeMillis() / 1000;
        final long expiry = timestamp + expiryDurationSeconds; // now + timeToExpireMs

        // Create the auth message
        final String chainIdHex = Felt.fromShortString(chainId).hexString();
        final String authMessage = createAuthMessage(timestamp, expiry, chainIdHex);

        // Convert the auth message to a TypedData object
        final SignatureResult signature = signMessage(authMessage, paradexAddress, starknetPrivateKey);
        return new AuthRequest(paradexAddress, signature.signature(), timestamp, expiry);
    }

    public static SignatureResult signMessage(String message, String accountAddressStr, String privateAddressStr) {
        final Felt accountAddress = Felt.fromHex(accountAddressStr);
        final Felt privateKeyFelt = Felt.fromHex(privateAddressStr);
        return signMessage(message, accountAddress, privateKeyFelt);
    }

    public static SignatureResult signMessage(String message, Felt accountAddress, Felt privateAddress) {
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


    @NotNull
    private static List<BigInteger> generateSignature(String authMessage, Felt privateKeyFelt, Felt accountAddress) {
        final TypedData typedData = TypedData.fromJsonString(authMessage);
        // Create new StarkCurveSigner with the private key
        final StarkCurveSigner scSigner = new StarkCurveSigner(privateKeyFelt);

        // Sign the typed data
        final List<Felt> signature = scSigner.signTypedData(typedData, accountAddress);

        // Convert the signature to a string
        final List<BigInteger> signatureBigInt = signature.stream()
                .map(Felt::getValue)
                .collect(Collectors.toList());
        return signatureBigInt;
    }

    private static String convertBigIntListToSignatureString(List<BigInteger> list) {
        return list.stream()
                .map(BigInteger::toString)
                .map(s -> "\"" + s + "\"") // Wrap each number in double quotes
                .collect(Collectors.joining(",", "[", "]")); // Keep brackets for a tuple-like format
    }

    public static String signOnboardingMessage(String paradexAddress, String starknetPrivateKey, String chainId) {
        final Felt accountAddress = Felt.fromHex(paradexAddress);
        final Felt privateKeyFelt = Felt.fromHex(starknetPrivateKey);
        final String chainIdHex = Felt.fromShortString(chainId).hexString();
        final String onboardingMessage = String.format(
                """
                        {
                            "message": {
                                "action": "Onboarding"
                            },
                            "domain": {"name": "Paradex", "chainId": "%s", "version": "1"},
                            "primaryType": "Constant",
                            "types": {
                                "StarkNetDomain": [
                                    {"name": "name", "type": "felt"},
                                    {"name": "chainId", "type": "felt"},
                                    {"name": "version", "type": "felt"}
                                ],
                                "Constant": [
                                    {"name": "action", "type": "felt"}
                                ]
                            }
                        }
                        """,
                chainIdHex
        );

        final List<BigInteger> signatureBigInt = generateSignature(onboardingMessage, privateKeyFelt, accountAddress);
        return convertBigIntListToSignatureString(signatureBigInt);
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
