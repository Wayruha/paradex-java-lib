package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PositionSide {
    SHORT("SHORT"),
    LONG("LONG");

    @JsonValue
    private final String name;
}
