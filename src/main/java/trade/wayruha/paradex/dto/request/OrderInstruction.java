package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderInstruction {
    Gtc("GTC"),
    Ioc("IOC"),
    PostOnly("POST_ONLY");

    @Getter
    @JsonValue
    private final String name;
}
