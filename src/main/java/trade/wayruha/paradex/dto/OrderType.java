package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {
    MARKET("MARKET"),
    LIMIT("LIMIT"),
    STOP_LIMIT("STOP_LIMIT"),
    STOP_MARKET("STOP_MARKET"),
    TAKE_PROFIT_LIMIT("TAKE_PROFIT_LIMIT"),
    TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),
    STOP_LOSS_MARKET("STOP_LOSS_MARKET"),
    STOP_LOSS_LIMIT("STOP_LOSS_LIMIT");

    @JsonValue
    private final String name;
}
