package trade.wayruha.paradex.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketBboResponse {
    private BigDecimal ask;
    private BigDecimal ask_size;
    private BigDecimal bid;
    private BigDecimal bid_size;
    private Long last_updated_at;
    private String market;
    private Long seq_no;
}
