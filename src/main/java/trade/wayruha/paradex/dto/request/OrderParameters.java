package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import trade.wayruha.paradex.dto.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParameters {
    @JsonIgnore
    private TimeInForce instruction;

    /**
     * e.g. BTC-USD-PERP
     */
    private String market;
    private BigDecimal price;
    private OrderSide side;
    private OrderType type;
    private BigDecimal size;

    /**
     * unique id assigned by user
     */
    @JsonProperty("client_id")
    private String clientId;

    /**
     * Order flags, allow flag: REDUCE_ONLY
     */
    @JsonIgnore
    private List<OrderFlag> flags;

    /**
     * Self Trade Prevention, EXPIRE_MAKER, EXPIRE_TAKER or EXPIRE_BOTH, if empty EXPIRE_TAKER
     */
    @JsonIgnore
    @JsonProperty("stp")
    private SelfTradePrevention selfTradePrevention;
    @Nullable
    @JsonIgnore
    private BigDecimal triggerPrice;
}
