package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllBalancesResponse {
    @JsonProperty("results")
    private List<BalanceItem> balances;
}
