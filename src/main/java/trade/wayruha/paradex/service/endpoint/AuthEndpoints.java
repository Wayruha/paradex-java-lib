package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import trade.wayruha.paradex.dto.response.AuthResponse;

public interface AuthEndpoints {

    @POST("auth")
    Call<AuthResponse> authenticate(
            @Header("PARADEX-STARKNET-ACCOUNT") String account,
            @Header("PARADEX-STARKNET-SIGNATURE") String signature,
            @Header("PARADEX-TIMESTAMP") String timestamp,
            @Header("PARADEX-SIGNATURE-EXPIRATION") String expiration
    );
}
