package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TimeInForce {
    Gtc("GTC"),
    Ioc("IOC"),
    PostOnly("POST_ONLY");

    @JsonValue
    private final String name;
}