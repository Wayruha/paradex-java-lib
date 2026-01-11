package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.MarginType;
import trade.wayruha.paradex.dto.response.AuthResponse;
import trade.wayruha.paradex.service.AccountService;
import trade.wayruha.paradex.service.AuthService;

import static trade.wayruha.paradex.TestUtils.*;

public class AccountEndpointsTest {
    private static AccountService accountService;
    private static AuthService authService;

    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig(false);
        config.setParadexAddress(ETH_ADDRESS);
        config.setParadexAddress(PARADEX_ADDRESS);
        config.setStarknetPublicKey(PUBLIC_KEY);
        config.setStarknetPrivateKey(PRIVATE_KEY);
        authService = new AuthService(config);

        AuthResponse response = authService.authenticate();
        config.setJwtToken(response.getJwtToken());
        System.out.println(response);

        accountService = new AccountService(config);

//        testGetAccountInfo();
//        testGetAllBalances();
        testUpdateLeverage();
    }

    private static void testGetAccountInfo() {
        System.out.println(accountService.getAccountInfo());
    }

    private static void testGetAllBalances() {
        System.out.println(accountService.getAllBalances());
    }

    private static void testUpdateLeverage() {
        System.out.println(accountService.setMarginConfig("BTC-USD-PERP", 3, MarginType.CROSS));
    }

}
