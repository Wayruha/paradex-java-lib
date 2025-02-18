package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.OrderStatus;
import trade.wayruha.paradex.dto.PositionSide;

@Data
public class PositionDetailsResponse {
    @JsonProperty("average_entry_price")
    private String averageEntryPrice;

    @JsonProperty("average_entry_price_usd")
    private String averageEntryPriceUsd;

    @JsonProperty("average_exit_price")
    private String averageExitPrice;

    @JsonProperty("cached_funding_index")
    private String cachedFundingIndex;

    @JsonProperty("closed_at")
    private Long closedAt;
    private String cost;

    @JsonProperty("cost_usd")
    private String costUsd;

    @JsonProperty("created_at")
    private Long createdAt;
    private String id;

    @JsonProperty("last_fill_id")
    private String lastFillId;

    @JsonProperty("last_updated_at")
    private Long lastUpdatedAt;
    private String leverage;

    @JsonProperty("liquidation_price")
    private String liquidationPrice;
    /**
     * Trading symbol
     */
    private String market;

    @JsonProperty("realized_positional_funding_pnl")
    private String realizedPositionalFundingPnl;

    @JsonProperty("realized_positional_pnl")
    private String realizedPositionalPnl;

    @JsonProperty("seq_no")
    private Long seqNo;
    private PositionSide side;
    private String size;
    private OrderStatus status;

    @JsonProperty("unrealized_funding_pnl")
    private String unrealizedFundingPnl;

    @JsonProperty("unrealized_pnl")
    private String unrealizedPnl;
}
