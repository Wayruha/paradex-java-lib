package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.OrderSide;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderBookUpdate {
    @JsonProperty("seq_no")
    private long seqNo;

    private String market;

    @JsonProperty("last_updated_at")
    private long lastUpdatedAt;

    @JsonProperty("update_type")
    private OrderBookUpdateType updateType;

    private List<Order> deletes;
    private List<Order> inserts;
    private List<Order> updates;

    @Data
    public static class Order {
        private OrderSide side;
        private BigDecimal price;
        private BigDecimal size;
    }
}