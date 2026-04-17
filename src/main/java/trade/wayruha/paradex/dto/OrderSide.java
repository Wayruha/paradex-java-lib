package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderSide {
    BUY("BUY", "1"),
    SELL("SELL", "2");

    @JsonValue
    private final String name;
    private final String code;
}
