package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.util.AuthRequestBuilder;

public class AuthTest {

    private static AuthService authService;

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig();
        authService = new AuthService(config);

        final Long hours24 = (long) (24 * 60 * 60); //todo: maybe move to config, but the value is optional

        AuthRequest authRequest = AuthRequestBuilder.buildRequest(config.getPublicKey(), config.getPrivateKey(), config.getChainId(), hours24);

        AuthResponse response = authService.authenticate(authRequest);
        System.out.println(response);
    }
}
