package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.OrderFlag;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderStatus;
import trade.wayruha.paradex.dto.OrderType;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDetailsResponse {
    private String account;

    @JsonProperty("avg_fill_price")
    private String avgFillPrice;

    @JsonProperty("cancel_reason")
    private String cancelReason;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("created_at")
    private Long createdAt;

    private List<OrderFlag> flags;
    private String id;
    private String instruction;

    @JsonProperty("last_updated_at")
    private Long lastUpdatedAt;

    private String market;
    private BigDecimal price;

    @JsonProperty("published_at")
    private Long publishedAt;

    @JsonProperty("received_at")
    private Long receivedAt;

    @JsonProperty("remaining_size")
    private String remainingSize;

    @JsonProperty("seq_no")
    private Long seqNo;

    private OrderSide side;
    private BigDecimal size;
    private OrderStatus status;
    private String stp;
    private Long timestamp;

    @JsonProperty("trigger_price")
    private BigDecimal triggerPrice;

    private OrderType type;
}
