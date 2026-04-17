package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import trade.wayruha.paradex.dto.request.UpdateMarginType;
import trade.wayruha.paradex.dto.response.AccountInfoResponse;
import trade.wayruha.paradex.dto.response.AllBalancesResponse;
import trade.wayruha.paradex.dto.response.UpdateMarginResponse;

public interface AccountEndpoints {

    @GET("account")
    Call<AccountInfoResponse> getAccountInfo();

    @GET("balance")
    Call<AllBalancesResponse> getAllBalances();

    @POST("account/margin/{market}")
    Call<UpdateMarginResponse> updateMarginType(@Path("market") String market, @Body UpdateMarginType dto);
}
