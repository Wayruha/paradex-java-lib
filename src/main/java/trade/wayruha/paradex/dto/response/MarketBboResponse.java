package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketBboResponse {
    private String market;
    private BigDecimal ask;
    @JsonProperty("ask_size")
    private BigDecimal askSize;
    private BigDecimal bid;
    @JsonProperty("bid_size")
    private BigDecimal bidSize;
    @JsonProperty("last_updated_at")
    private Long lastUpdatedAt;
    @JsonProperty("seq_no")
    private Long sequenceNumber;
}
