package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import trade.wayruha.paradex.dto.MarginType;

@Data
@AllArgsConstructor
public class UpdateMarginType {
    private int leverage;
    @JsonProperty("margin_type")
    private MarginType marginType;
}
