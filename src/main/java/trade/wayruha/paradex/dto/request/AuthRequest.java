package trade.wayruha.paradex.dto.request;

import lombok.Value;

/**
 * Represents the authentication request payload for the Paradex API.
 */

@Value
public class AuthRequest {
    /**
     * The StarkNet account identifier used for authentication.
     */
    String account;

    /**
     * The StarkNet signature associated with the request.
     */
    String signature;

    /**
     * The timestamp when the request is made, in EIP-712 format.
     */
    long timestamp;

    /**
     * The expiration timestamp for the signature, ensuring the request is time-bound.
     */
    long signatureExpiration;
}
