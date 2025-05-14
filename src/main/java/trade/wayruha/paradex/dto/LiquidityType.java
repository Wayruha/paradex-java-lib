package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LiquidityType {
    TAKER("TAKER"),
    MAKER("MAKER");

    @Getter
    @JsonValue
    private final String name;
}

