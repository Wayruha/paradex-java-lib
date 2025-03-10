package trade.wayruha.paradex.service;

import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.request.OnboardRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.endpoint.AuthEndpoints;
import trade.wayruha.paradex.util.AuthRequestBuilder;

public class AuthService extends ServiceBase {
    final Long DAY_IN_SEC = (long) (24 * 60 * 60);

    private final AuthEndpoints authApi;
    private final AuthRequestBuilder authRequestBuilder;

    public AuthService(ParadexConfig config) {
        super(config);
        this.authApi = createService(AuthEndpoints.class);
        this.authRequestBuilder = new AuthRequestBuilder(config.getPrivateKey(), config.getPublicKey(), config.getChainId());
    }

    /**
     * Authenticate with Paradex API.
     * @return AuthResponse containing JWT authentication token
     */
    public AuthResponse authenticate(){
       return authenticate(DAY_IN_SEC);
    }

    public AuthResponse authenticate(long validityPeriodSec){
        final AuthRequest authRequest = authRequestBuilder.buildRequest(validityPeriodSec);
        return client.executeSync(authApi.authenticate(authRequest.getAccount(),
                authRequest.getSignature(),
                authRequest.getTimestamp(),
                authRequest.getSignatureExpiration()));
    }

    /**
     *
     * @param ethAccount ETH wallet used to sign in to Paradex UI
     * @param starknetAccount can be found on Paradex ui as "Paradex Account"
     * @param privateKey This is available directly in Paradex. PLEASE NEVER SHARE THIS WITH ANYONE EVEN IF THEY SAY THEY WORK FOR PARADEX
     * @param publicKey To find this value go to https://voyager.prod.paradex.trade/, search for your Paradex Account, and under Contract data choose the getSigner function from Read Contract
     * @param referralCode (optional) code generated on Paradex UI
     */
    public void onboard(String ethAccount, String starknetAccount, String privateKey, String publicKey, String referralCode) {
        client.executeSync(authApi.onboard(
                ethAccount,
                starknetAccount,
                authRequestBuilder.signOnboardingMessage(privateKey),
                new OnboardRequest(publicKey, referralCode)
        ));
    }
}
