package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.OrderStatus;
import trade.wayruha.paradex.dto.PositionSide;

import java.math.BigDecimal;

@Data
public class PositionDetailsResponse {
    @JsonProperty("average_entry_price")
    private BigDecimal averageEntryPrice;

    @JsonProperty("average_entry_price_usd")
    private BigDecimal averageEntryPriceUsd;

    @JsonProperty("average_exit_price")
    private BigDecimal averageExitPrice;

    @JsonProperty("cached_funding_index")
    private String cachedFundingIndex;

    @JsonProperty("closed_at")
    private Long closedAt;
    private BigDecimal cost;

    @JsonProperty("cost_usd")
    private BigDecimal costUsd;

    @JsonProperty("created_at")
    private Long createdAt;
    private String id;

    @JsonProperty("last_fill_id")
    private String lastFillId;

    @JsonProperty("last_updated_at")
    private Long lastUpdatedAt;
    private double leverage;

    @JsonProperty("liquidation_price")
    private BigDecimal liquidationPrice;
    /**
     * Trading symbol
     */
    private String market;

    @JsonProperty("realized_positional_funding_pnl")
    private BigDecimal realizedPositionalFundingPnl;

    @JsonProperty("realized_positional_pnl")
    private BigDecimal realizedPositionalPnl;

    @JsonProperty("seq_no")
    private Long sequenceNumber;
    private PositionSide side;
    private BigDecimal size;
    private OrderStatus status;

    @JsonProperty("unrealized_funding_pnl")
    private BigDecimal unrealizedFundingPnl;

    @JsonProperty("unrealized_pnl")
    private BigDecimal unrealizedPnl;
}
