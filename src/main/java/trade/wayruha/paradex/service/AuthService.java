package trade.wayruha.paradex.service;

import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.endpoint.AuthEndpoints;
import trade.wayruha.paradex.util.AuthRequestBuilder;

public class AuthService extends ServiceBase {
    final Long DAY_IN_SEC = (long) (24 * 60 * 60);

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

    public AuthResponse authenticate(){
       return authenticate(DAY_IN_SEC);
    }

    public AuthResponse authenticate(long validityPeriodSec){
        final ParadexConfig config = client.getConfig();
        final AuthRequest authRequest = AuthRequestBuilder.buildRequest(config.getPublicKey(), config.getPrivateKey(), config.getChainId(), validityPeriodSec);
        return client.executeSync(authApi.authenticate(authRequest.getAccount(),
                authRequest.getSignature(),
                authRequest.getTimestamp(),
                authRequest.getSignatureExpiration()));
    }
}
