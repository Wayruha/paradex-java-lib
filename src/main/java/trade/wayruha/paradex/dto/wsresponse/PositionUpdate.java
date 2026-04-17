package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionUpdate {
    @JsonProperty("average_entry_price")
    private BigDecimal averageEntryPrice;

    @JsonProperty("average_entry_price_usd")
    private BigDecimal averageEntryPriceUsd;

    @JsonProperty("average_exit_price")
    private BigDecimal averageExitPrice;

    @JsonProperty("cached_funding_index")
    private BigDecimal cachedFundingIndex;

    private BigDecimal cost;

    @JsonProperty("cost_usd")
    private BigDecimal costUsd;

    private String id;

    @JsonProperty("last_fill_id")
    private String lastFillId;

    private String leverage;

    @JsonProperty("liquidation_price")
    private String liquidationPrice;

    private String market;

    @JsonProperty("realized_positional_funding_pnl")
    private String realizedPositionalFundingPnl;

    @JsonProperty("realized_positional_pnl")
    private String realizedPositionalPnl;

    @JsonProperty("seq_no")
    private long seqNo;

    private String side;

    private BigDecimal size;

    private String status;

    @JsonProperty("unrealized_funding_pnl")
    private BigDecimal unrealizedFundingPnl;

    @JsonProperty("unrealized_pnl")
    private BigDecimal unrealizedPnl;
}
