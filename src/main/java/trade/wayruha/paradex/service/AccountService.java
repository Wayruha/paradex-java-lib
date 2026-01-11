package trade.wayruha.paradex.service;

import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.MarginType;
import trade.wayruha.paradex.dto.request.UpdateMarginType;
import trade.wayruha.paradex.dto.response.AccountInfoResponse;
import trade.wayruha.paradex.dto.response.AllBalancesResponse;
import trade.wayruha.paradex.dto.response.UpdateMarginResponse;
import trade.wayruha.paradex.service.endpoint.AccountEndpoints;

public class AccountService extends ServiceBase {
    private final AccountEndpoints accountApi;

    public AccountService(ParadexConfig config) {
        super(config);
        this.accountApi = createService(AccountEndpoints.class);
    }

    public AccountInfoResponse getAccountInfo() {
        return client.executeSync(accountApi.getAccountInfo());
    }

    public AllBalancesResponse getAllBalances() {
        return client.executeSync(accountApi.getAllBalances());
    }

    public UpdateMarginResponse setMarginConfig(String market, int leverage, MarginType marginType) {
        return client.executeSync(accountApi.updateMarginType(market, new UpdateMarginType(leverage, marginType)));
    }
}
