package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderFlag {
    REDUCE_ONLY("REDUCE_ONLY"),
    STOP_CONDITION_BELOW_TRIGGER("STOP_CONDITION_BELOW_TRIGGER"),
    STOP_CONDITION_ABOVE_TRIGGER("STOP_CONDITION_ABOVE_TRIGGER"),
    TARGET_STRATEGY_VWAP("TARGET_STRATEGY_VWAP"),
    INTERACTIVE("INTERACTIVE");

    @Getter
    @JsonValue
    private final String name;
}
