package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {
    New("NEW"),
    Untriggered("UNTRIGGERED"),
    Open("OPEN"),
    Closed("CLOSED");

    @Getter
    @JsonValue
    private final String name;
}
