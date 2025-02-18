package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.jetbrains.annotations.Nullable;
import trade.wayruha.paradex.dto.OrderFlag;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderType;
import trade.wayruha.paradex.dto.SelfTradePrevention;

import java.math.BigDecimal;
import java.util.List;

@Value
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
     * Order flags, allow flag: REDUCE_ONLY
     */
    @JsonIgnore
    List<OrderFlag> flags;
    /**
     * if empty EXPIRE_TAKER
     */
    @JsonIgnore
    SelfTradePrevention selfTradePrevention;
    @Nullable
    @JsonIgnore
    BigDecimal triggerPrice;
}
