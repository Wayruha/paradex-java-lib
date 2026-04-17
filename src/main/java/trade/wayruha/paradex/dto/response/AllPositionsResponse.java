package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllPositionsResponse {
    @JsonProperty("results")
    private List<PositionDetailsResponse> positions;
}
