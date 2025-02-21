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

        AuthResponse response = authService.authenticate();
        System.out.println(response);
    }
}
