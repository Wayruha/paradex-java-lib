package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderFlag {
    REDUCE_ONLY("REDUCE_ONLY"),
    STOP_CONDITION_BELOW_TRIGGER("STOP_CONDITION_BELOW_TRIGGER"),
    STOP_CONDITION_ABOVE_TRIGGER("STOP_CONDITION_ABOVE_TRIGGER");

    @Getter
    @JsonValue
    private final String name;
}
