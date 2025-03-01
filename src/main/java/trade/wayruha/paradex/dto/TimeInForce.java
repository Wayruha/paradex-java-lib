package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TimeInForce {
    GTC("GTC"),
    IOC("IOC"),
    POST_ONLY("POST_ONLY");

    @JsonValue
    private final String name;
}