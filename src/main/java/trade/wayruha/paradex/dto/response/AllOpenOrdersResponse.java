package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllOpenOrdersResponse {
    @JsonProperty("results")
    private List<OrderDetailsResponse> orders;
}
