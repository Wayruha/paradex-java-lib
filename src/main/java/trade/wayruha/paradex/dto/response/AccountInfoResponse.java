package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountInfoResponse {

    /**
     * User’s starknet account
     */
    @JsonProperty("account")
    String account;

    /**
     * Current account value [with unrealized P&Ls]
     */
    @JsonProperty("account_value")
    BigDecimal accountValue;

    /**
     * Free collateral available (Account value in excess of Initial Margin required)
     */
    @JsonProperty("free_collateral")
    BigDecimal freeCollateral;

    @JsonProperty("initial_margin_requirement")
    BigDecimal initialMarginRequirement;

    @JsonProperty("maintenance_margin_requirement")
    BigDecimal maintenanceMarginRequirement;

    @JsonProperty("margin_cushion")
    BigDecimal marginCushion;

    /**
     * Unique increasing number (non-sequential) that is assigned to this account update. Can be used to deduplicate multiple feeds
     */
    @JsonProperty("seq_no")
    long seqNo;

    @JsonProperty("settlement_asset")
    String settlementAsset;
    String status;

    @JsonProperty("total_collateral")
    BigDecimal totalCollateral;

    @JsonProperty("updated_at")
    long updatedAt;
}
