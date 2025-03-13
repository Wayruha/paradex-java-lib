package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import trade.wayruha.paradex.dto.request.OnboardRequest;
import trade.wayruha.paradex.dto.response.AuthResponse;

public interface AuthEndpoints {

    @POST("auth")
    Call<AuthResponse> authenticate(
            @Header("PARADEX-STARKNET-ACCOUNT") String account,
            @Header("PARADEX-STARKNET-SIGNATURE") String signature,
            @Header("PARADEX-TIMESTAMP") long timestamp,
            @Header("PARADEX-SIGNATURE-EXPIRATION") long expiration
    );

    @POST("onboarding")
    Call<Void> onboard(
            @Header("PARADEX-ETHEREUM-ACCOUNT") String ethAccount,
            @Header("PARADEX-STARKNET-ACCOUNT") String starknetAccount,
            @Header("PARADEX-STARKNET-SIGNATURE") String signature,
            @Body OnboardRequest onboardRequest
    );
}
