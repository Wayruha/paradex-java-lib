package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AuthService;

public class AuthTest {

    private static AuthService authService;

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig(true);
        config.setEthAddress("");
        config.setParadexAddress("");
        config.setStarknetPublicKey("");
        config.setStarknetPrivateKey("");
        authService = new AuthService(config);

        onboard();
        authenticate();
    }


    public static void authenticate() {
        AuthResponse response = authService.authenticate();
        System.out.println(response);
    }

    public static void onboard() {
        authService.onboard("");
    }
}
