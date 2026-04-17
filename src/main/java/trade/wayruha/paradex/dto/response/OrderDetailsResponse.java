package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailsResponse {
    private String id;
    private String account;

    @JsonProperty("avg_fill_price")
    private BigDecimal avgFillPrice;

    @JsonProperty("cancel_reason")
    private CancelReason cancelReason;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("created_at")
    private Long createdAt;

    private List<OrderFlag> flags;

    @JsonProperty("instruction")
    private TimeInForce timeInForce;

    @JsonProperty("last_updated_at")
    private Long lastUpdatedAt;

    private String market;

    private BigDecimal price;

    @JsonProperty("published_at")
    private Long publishedAt;

    @JsonProperty("received_at")
    private Long receivedAt;

    @JsonProperty("remaining_size")
    private BigDecimal remainingSize;

    @JsonProperty("seq_no")
    private Long sequenceNumber;

    private OrderSide side;
    private BigDecimal size;
    private OrderStatus status;
    @JsonProperty("stp")
    private SelfTradePrevention selfTradePrevention;
    private Long timestamp;

    @JsonProperty("trigger_price")
    private BigDecimal triggerPrice;

    private OrderType type;
}
