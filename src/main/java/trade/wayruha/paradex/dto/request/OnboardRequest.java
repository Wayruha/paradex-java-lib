package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnboardRequest {
    @JsonProperty("public_key")
    String publicKey;

    @JsonProperty("referral_code")
    String referralCode;
}
