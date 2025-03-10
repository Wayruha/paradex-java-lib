package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AuthService;

public class AuthTest {

    private static AuthService authService;

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig(true);
        authService = new AuthService(config);

        AuthResponse response = authService.authenticate();
        System.out.println(response);
    }
}
