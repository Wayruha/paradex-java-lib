package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketSummaryItem {
    private BigDecimal ask;

    @JsonProperty("ask_iv")
    private BigDecimal askIv; //ask implied volatility, for options
    private BigDecimal bid;

    @JsonProperty("bid_iv")
    private BigDecimal bidIv; //bid implied volatility, for options

    @JsonProperty("created_at")
    private Long createdAt;
    private String delta; //deprecated

    @JsonProperty("funding_rate")
    private String fundingRate;

    @JsonProperty("future_funding_rate")
    private String futureFundingRate; //Future funding rate, for options
    private Greeks greeks;

    @JsonProperty("last_iv")
    private String lastIv; //last traded price volatility, for options

    @JsonProperty("last_traded_price")
    private BigDecimal lastTradedPrice;

    @JsonProperty("mark_iv")
    private String markIv; //mark implied volatility, for options

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
    @JsonProperty("mark_price")
    private BigDecimal markPrice;

    @JsonProperty("open_interest")
    private BigDecimal openInterest; //open interest in base currency

    @JsonProperty("price_change_rate_24h")
    private BigDecimal priceChangeRate24h;
    private String symbol;

    @JsonProperty("total_volume")
    private BigDecimal totalVolume; //Lifetime total traded volume in USD

    @JsonProperty("underlying_price")
    private BigDecimal underlyingPrice; //Underlying asset price (spot oracle price)

    @JsonProperty("volume_24h")
    private BigDecimal volume24h; //24 hour volume in USD

    @Data
    private class Greeks {
        private String delta; //market delta
        private String gamma; //market gamma
        private String vega; //market vega
    }
}
