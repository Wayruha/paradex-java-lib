package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SelfTradePrevention {
    ExpireMaker("EXPIRE_MAKER"),
    ExpireTaker("EXPIRE_TAKER"),
    ExpireBoth("EXPIRE_BOTH");

    @Getter
    @JsonValue
    private final String name;
}
