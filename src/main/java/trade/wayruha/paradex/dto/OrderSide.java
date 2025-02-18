package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderSide {
    Buy("BUY"),
    Sell("SELL");

    @Getter
    @JsonValue
    private final String name;
}
