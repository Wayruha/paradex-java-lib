package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails {
    private String account;

    @JsonProperty("account_value")
    private BigDecimal accountValue;

    @JsonProperty("free_collateral")
    private BigDecimal freeCollateral;

    @JsonProperty("initial_margin_requirement")
    private BigDecimal initialMarginRequirement;

    @JsonProperty("maintenance_margin_requirement")
    private BigDecimal maintenanceMarginRequirement;

    @JsonProperty("margin_cushion")
    private BigDecimal marginCushion;

    @JsonProperty("seq_no")
    private long seqNo;

    @JsonProperty("settlement_asset")
    private String settlementAsset;

    private String status;

    @JsonProperty("total_collateral")
    private BigDecimal totalCollateral;

    @JsonProperty("updated_at")
    private long updatedAt;
}