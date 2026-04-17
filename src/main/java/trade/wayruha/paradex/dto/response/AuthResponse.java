package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponse {
    @JsonProperty("jwt_token")
    private String jwtToken;

    @JsonIgnore
    private long expireAt;
}
