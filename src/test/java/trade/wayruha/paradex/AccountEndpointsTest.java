package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.request.AuthRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AccountService;
import trade.wayruha.paradex.service.AuthService;
import trade.wayruha.paradex.service.MarketService;
import trade.wayruha.paradex.util.AuthRequestBuilder;

public class AccountEndpointsTest {
    static final String PUBLIC_KEY = "";
    static final String PRIVATE_KEY = "";

    private static AccountService accountService;
    private static AuthService authService;
    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig();

        config.setPublicKey(PUBLIC_KEY);
        config.setPrivateKey(PRIVATE_KEY);
        authService = new AuthService(config);

        final Long hours24 = (long) (24 * 60 * 60);

        AuthRequest authRequest = AuthRequestBuilder.buildRequest(config.getPublicKey(), config.getPrivateKey(), config.getChainId(), hours24);
        System.out.println(authRequest);
        AuthResponse response = authService.authenticate(authRequest);
        config.setJwtToken(response.getJwtToken());
        System.out.println(config.getJwtToken());

        accountService = new AccountService(config);

        testGetAccountInfo();
        testGetAllBalances();
    }

    private static void testGetAccountInfo(){
        System.out.println(accountService.getAccountInfo());
    }
    private static void testGetAllBalances(){
        System.out.println(accountService.getAllBalances());
    }

}
