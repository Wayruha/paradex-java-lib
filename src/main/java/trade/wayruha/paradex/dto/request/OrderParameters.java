package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import trade.wayruha.paradex.dto.OrderFlag;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.SelfTradePrevention;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParameters {
    @JsonIgnore
    OrderInstruction instruction;

    /**
     * e.g. BTC-USD-PERP
     */
    String market;
    BigDecimal price;
    OrderSide side;
    OrderType type;
    BigDecimal size;

    /**
     * unique id assigned by user
     */
    @JsonProperty("client_id")
    String clientId;

    /**
     * Order flags, allow flag: REDUCE_ONLY
     */
    @JsonIgnore
    List<OrderFlag> flags;

    /**
     * Self Trade Prevention, EXPIRE_MAKER, EXPIRE_TAKER or EXPIRE_BOTH, if empty EXPIRE_TAKER
     */
    @JsonIgnore
    @JsonProperty("stp")
    SelfTradePrevention selfTradePrevention;
    @Nullable
    @JsonIgnore
    BigDecimal triggerPrice;
}
