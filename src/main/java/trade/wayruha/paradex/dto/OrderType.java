package trade.wayruha.paradex.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderType {
    Market("MARKET"),
    Limit("LIMIT"),
    StopLimit("STOP_LIMIT"),
    StopMarket("STOP_MARKET"),
    TakeProfitLimit("TAKE_PROFIT_LIMIT"),
    TakeProfitMarket("TAKE_PROFIT_MARKET"),
    StopLossMarket("STOP_LOSS_MARKET"),
    StopLossLimit("STOP_LOSS_LIMIT");

    @Getter
    @JsonValue
    private final String name;
}
