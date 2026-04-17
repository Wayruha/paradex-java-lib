package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.wayruha.paradex.dto.MarginType;

@Data
public class UpdateMarginResponse {
    private String account;
    private int leverage;
    @JsonProperty("margin_type")
    private MarginType marginType;
    private String market;
}
