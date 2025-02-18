package trade.wayruha.paradex.service;

import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.endpoint.AuthEndpoints;

public class AuthService extends ServiceBase {
    private final AuthEndpoints authApi;

    public AuthService(ParadexConfig config) {
        super(config);
        this.authApi = createService(AuthEndpoints.class);
    }

    /**
     * Authenticate with Paradex API.
     * @return AuthResponse containing JWT authentication token
     */
    public AuthResponse authenticate(AuthRequest authRequest) {
        return client.executeSync(authApi.authenticate(authRequest.getAccount(),
                authRequest.getSignature(),
                authRequest.getTimestamp(),
                authRequest.getSignatureExpiration()));
    }

}
