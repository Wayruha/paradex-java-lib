package trade.wayruha.paradex.service;

import com.auth0.jwt.JWT;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.request.OnboardRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.endpoint.AuthEndpoints;
import trade.wayruha.paradex.util.SigningUtil;

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
    public AuthResponse authenticate() {
        return authenticate(DAY_IN_SEC);
    }

    public AuthResponse authenticate(long validityPeriodSec) {
        final AuthRequest authRequest = SigningUtil.buildRequest(getConfig().getParadexAddress(), getConfig().getStarknetPrivateKey(), getConfig().getChainId(), validityPeriodSec);
        final AuthResponse authResponse = client.executeSync(authApi.authenticate(authRequest.getAccount(),
                authRequest.getSignature(),
                authRequest.getTimestamp(),
                authRequest.getSignatureExpiration()));
        authResponse.setExpireAt(decodeAndGetExpiration(authResponse.getJwtToken()));
        return authResponse;
    }

    private long decodeAndGetExpiration(String jwtToken) {
        return JWT.decode(jwtToken).getExpiresAt().toInstant().getEpochSecond();
    }

    /**
     * @param referralCode (optional) code generated on Paradex UI
     */
    public void onboard(String referralCode) {
        client.executeSync(authApi.onboard(
                getConfig().getEthAddress(),
                getConfig().getParadexAddress(),
                SigningUtil.signOnboardingMessage(getConfig().getParadexAddress(), getConfig().getStarknetPrivateKey(), getConfig().getChainId()),
                new OnboardRequest(getConfig().getStarknetPublicKey(), referralCode)
        ));
    }
}
