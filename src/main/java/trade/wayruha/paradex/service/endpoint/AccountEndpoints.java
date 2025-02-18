package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import trade.wayruha.paradex.dto.response.AccountInfoResponse;
import trade.wayruha.paradex.dto.response.AllBalancesResponse;

public interface AccountEndpoints {

    @GET("account")
    Call<AccountInfoResponse> getAccountInfo();

    @GET("balance")
    Call<AllBalancesResponse> getAllBalances();
}
