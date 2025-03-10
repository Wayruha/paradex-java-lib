package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AccountService;
import trade.wayruha.paradex.service.AuthService;

import static trade.wayruha.paradex.TestUtils.PRIVATE_KEY;
import static trade.wayruha.paradex.TestUtils.PUBLIC_KEY;

public class AccountEndpointsTest {
    private static AccountService accountService;
    private static AuthService authService;

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig(false);

        config.setPublicKey(PUBLIC_KEY);
        config.setPrivateKey(PRIVATE_KEY);
        authService = new AuthService(config);

        AuthResponse response = authService.authenticate();
        config.setJwtToken(response.getJwtToken());
        System.out.println(config.getJwtToken());

        accountService = new AccountService(config);

        testGetAccountInfo();
        testGetAllBalances();
    }

    private static void testGetAccountInfo() {
        System.out.println(accountService.getAccountInfo());
    }

    private static void testGetAllBalances() {
        System.out.println(accountService.getAllBalances());
    }

}
