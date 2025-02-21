/*
package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderUpdateDto {
    private String account;

    @JsonProperty("cancel_reason")
    private CancelReason cancelReason;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("created_at")
    private long createdAt;

    private String id;

    @JsonProperty("instruction")
    private TimeInForce timeInForce;

    @JsonProperty("last_updated_at")
    private long lastUpdatedAt;

    private String market;

    private BigDecimal price;

    @JsonProperty("remaining_size")
    private BigDecimal remainingSize;

    private OrderSide side;

    private BigDecimal size;

    private OrderStatus status;

    private long timestamp;

    private OrderType type;

    @JsonProperty("seq_no")
    private long seqNo;

    @JsonProperty("avg_fill_price")
    private BigDecimal avgFillPrice;

    @JsonProperty("received_at")
    private long receivedAt;

    @JsonProperty("published_at")
    private long publishedAt;

    private List<String> flags;

    @JsonProperty("trigger_price")
    private BigDecimal triggerPrice;
}*/
