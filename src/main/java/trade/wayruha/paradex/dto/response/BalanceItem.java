package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceItem {

    @JsonProperty("last_updated_at")
    long lastUpdatedAt;
    BigDecimal size;
    String token;
}
