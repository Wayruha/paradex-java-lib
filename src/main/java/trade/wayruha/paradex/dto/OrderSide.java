package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderSide {
    Buy("BUY", "1"),
    Sell("SELL", "2");

    @Getter
    @JsonValue
    private final String name;
    private final String code;
}
