package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SelfTradePrevention {
    EXPIRE_MAKER("EXPIRE_MAKER"),
    EXPIRE_TAKER("EXPIRE_TAKER"),
    EXPIRE_BOTH("EXPIRE_BOTH");

    @Getter
    @JsonValue
    private final String name;
}
