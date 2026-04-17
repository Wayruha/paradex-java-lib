package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceUpdate {
    @JsonProperty("created_at")
    private long createdAt;

    private BigDecimal fees;

    @JsonProperty("fill_id")
    private String fillId;

    @JsonProperty("funding_index")
    private BigDecimal fundingIndex;

    private String market;

    @JsonProperty("realized_funding")
    private BigDecimal realizedFunding;

    @JsonProperty("realized_pnl")
    private BigDecimal realizedPnl;

    @JsonProperty("settlement_asset_balance_after")
    private BigDecimal settlementAssetBalanceAfter;

    @JsonProperty("settlement_asset_balance_before")
    private BigDecimal settlementAssetBalanceBefore;

    @JsonProperty("settlement_asset_price")
    private BigDecimal settlementAssetPrice;

    private String status;

    private String type;
}
