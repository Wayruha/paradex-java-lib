package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.FillType;
import trade.wayruha.paradex.dto.LiquidityType;
import trade.wayruha.paradex.dto.OrderSide;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FillUpdate {

    private String account;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("created_at")
    private Long createdAt;

    private BigDecimal fee;

    @JsonProperty("fee_currency")
    private String feeCurrency;

    @JsonProperty("fill_type")
    private FillType fillType;

    private String id;

    private LiquidityType liquidity;

    private String market;

    @JsonProperty("order_id")
    private String orderId;

    private BigDecimal price;

    @JsonProperty("realized_funding")
    private BigDecimal realizedFunding;

    @JsonProperty("realized_pnl")
    private BigDecimal realizedPnl;

    @JsonProperty("remaining_size")
    private BigDecimal remainingSize;

    private OrderSide side;

    private BigDecimal size;

    @JsonProperty("underlying_price")
    private BigDecimal underlyingPrice;
}