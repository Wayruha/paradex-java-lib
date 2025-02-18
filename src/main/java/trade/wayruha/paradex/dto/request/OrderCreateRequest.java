package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import trade.wayruha.paradex.dto.OrderFlag;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.SelfTradePrevention;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderCreateRequest {
    @JsonIgnore
    OrderInstruction instruction;
    /**
     * e.g. BTC-USD-PERP
     */
    String market;
    BigDecimal price;
    OrderSide side;
    /**
     * Order Payload signed with STARK Private Key
     */
    String signature;

    @JsonProperty("signature_timestamp")
    long timestamp;
    BigDecimal size;
    OrderType type;

    @JsonProperty("client_id")
    String clientId;
    @JsonIgnore
    List<OrderFlag> flags;

    @JsonIgnore
    @JsonProperty("recv_window")
    int receiveWindow;

    /**
     * Self Trade Prevention, EXPIRE_MAKER, EXPIRE_TAKER or EXPIRE_BOTH, if empty EXPIRE_TAKER
     */
    @JsonIgnore
    @JsonProperty("stp")
    SelfTradePrevention selfTradePrevention;

    @JsonIgnore
    @JsonProperty("trigger_price")
    String triggerPrice;
}
