package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderHistoryResponse {
    private String next;
    private String prev;

    @JsonProperty("results")
    List<OrderDetailsResponse> orders;
}
