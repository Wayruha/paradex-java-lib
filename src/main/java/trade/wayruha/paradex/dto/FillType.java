package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FillType {
    FILL("FILL"),
    LIQUIDATION("LIQUIDATION"),
    TRANSFER("TRANSFER"),
    SETTLE_MARKET("SETTLE_MARKET");

    @Getter
    @JsonValue
    private final String name;
}
