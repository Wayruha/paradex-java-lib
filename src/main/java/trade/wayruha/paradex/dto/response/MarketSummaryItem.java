package trade.wayruha.paradex.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketSummaryItem {
    private BigDecimal ask;
    private BigDecimal ask_iv; //ask implied volatility, for options
    private BigDecimal bid;
    private BigDecimal bid_iv; //bid implied volatility, for options
    private Long created_at;
    private String delta; //deprecated
    private String funding_rate;
    private String future_funding_rate; //Future funding rate, for options
    private Greeks greeks; //todo
    private String last_iv; //last traded price volatility, for options
    private BigDecimal last_traded_price;
    private String mark_iv; //mark implied volatility, for options

    /**
     * The mark price of a perpetual future instrument is calculated as : markPrice = spotOraclePrice * (1 + FairBasis).
     * The Fair Basis is calculated as the median of three components:
     * <p>
     * - median between the EWMA of 3 rates implied from the best bid, best ask and last price
     * </p>
     * <p>
     * - EWMA of implied the Mid Rate
     * </p>
     * <p>
     * - median of External Rates
     * </p>
     * details at : https://docs.paradex.trade/documentation/risk-system/mark-price-calculation
     */
    private BigDecimal mark_price;
    private BigDecimal open_interest; //open interest in base currency
    private BigDecimal price_change_rate_24h;
    private String symbol;
    private BigDecimal total_volume; //Lifetime total traded volume in USD
    private BigDecimal underlying_price; //Underlying asset price (spot oracle price)
    private BigDecimal volume_24h; //24 hour volume in USD

    @Data
    private class Greeks{
        private String delta; //market delta
        private String gamma; //market gamma
        private String vega; //market vega
    }
}
