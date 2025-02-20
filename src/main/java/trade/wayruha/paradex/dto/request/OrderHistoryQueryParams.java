package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Value;
import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.OrderStatus;
import trade.wayruha.paradex.dto.OrderType;

import java.util.HashMap;
import java.util.Map;

@Value
@AllArgsConstructor
public class OrderHistoryQueryParams {
    String clientId;
    String cursor;    // Returns the ‘next’ paginated page.
    Long endAt;       // Unix time in milliseconds
    String market;
    Integer pageSize;
    OrderSide side;
    Long startAt;      // Unix time in milliseconds
    OrderStatus status;
    OrderType type;

    @JsonIgnore
    public Map<String, String> toQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        if (clientId != null) queryMap.put("client_id", clientId);
        if (cursor != null) queryMap.put("cursor", cursor);
        if (endAt != null) queryMap.put("end_at", String.valueOf(endAt));
        if (market != null) queryMap.put("market", market);
        if (pageSize != null) queryMap.put("page_size", String.valueOf(pageSize));
        if (side != null) queryMap.put("side", side.getName());
        if (startAt != null) queryMap.put("start_at", String.valueOf(startAt));
        if (status != null) queryMap.put("status", status.getName());
        if (type != null) queryMap.put("type", type.getName());
        return queryMap;
    }
}