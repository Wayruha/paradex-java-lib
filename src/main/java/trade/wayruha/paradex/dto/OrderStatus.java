package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    NEW("NEW"),
    UNTRIGGERED("UNTRIGGERED"),
    OPEN("OPEN"),
    CLOSED("CLOSED");

    @JsonValue
    private final String name;
}
