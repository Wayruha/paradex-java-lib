package trade.wayruha.paradex.dto.wsresponse;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderBookUpdateType {
    SNAPSHOT("s"), DELTA("d");

    @JsonValue
    @Getter
    private final String name;
}
