package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PositionSide {
    Short("SHORT"),
    Long("LONG");

    @Getter
    @JsonValue
    private final String name;
}
