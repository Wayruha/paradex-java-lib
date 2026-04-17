package trade.wayruha.paradex.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MarketSummaryResponse {
    private List<MarketSummaryItem> results;
}
