package trade.wayruha.paradex.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MarketStaticData {
    @JsonProperty("asset_kind")
    private String assetKind;

    @JsonProperty("base_currency")
    private String baseCurrency;

    @JsonProperty("chain_details")
    private ChainDetails chainDetails;

    @JsonProperty("clamp_rate")
    private BigDecimal clampRate;

    @JsonProperty("delta1_cross_margin_params")
    private MarginParams delta1CrossMarginParams;

    @JsonProperty("expiry_at")
    private long expiryAt;

    @JsonProperty("funding_period_hours")
    private int fundingPeriodHours;

    @JsonProperty("interest_rate")
    private BigDecimal interestRate;

    @JsonProperty("iv_bands_width")
    private String ivBandsWidth;

    @JsonProperty("market_kind")
    private String marketKind;

    @JsonProperty("max_funding_rate")
    private BigDecimal maxFundingRate;

    @JsonProperty("max_funding_rate_change")
    private BigDecimal maxFundingRateChange;

    @JsonProperty("max_open_orders")
    private int maxOpenOrders;

    @JsonProperty("max_order_size")
    private BigDecimal maxOrderSize;

    @JsonProperty("max_tob_spread")
    private BigDecimal maxTobSpread;

    @JsonProperty("min_notional")
    private BigDecimal minNotional;

    @JsonProperty("open_at")
    private long openAt;

    @JsonProperty("option_cross_margin_params")
    private OptionMarginParams optionCrossMarginParams;

    @JsonProperty("option_type")
    private String optionType;

    @JsonProperty("oracle_ewma_factor")
    private BigDecimal oracleEwmaFactor;

    @JsonProperty("order_size_increment")
    private BigDecimal orderSizeIncrement;

    @JsonProperty("position_limit")
    private BigDecimal positionLimit;

    @JsonProperty("price_bands_width")
    private BigDecimal priceBandsWidth;

    @JsonProperty("price_feed_id")
    private String priceFeedId;

    @JsonProperty("price_tick_size")
    private BigDecimal priceTickSize;

    @JsonProperty("quote_currency")
    private String quoteCurrency;

    @JsonProperty("settlement_currency")
    private String settlementCurrency;

    @JsonProperty("strike_price")
    private String strikePrice;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("tags")
    private List<String> tags;

    static class ChainDetails {
        @JsonProperty("collateral_address")
        private String collateralAddress;

        @JsonProperty("contract_address")
        private String contractAddress;

        @JsonProperty("fee_account_address")
        private String feeAccountAddress;

        @JsonProperty("fee_maker")
        private String feeMaker;

        @JsonProperty("fee_taker")
        private String feeTaker;

        @JsonProperty("insurance_fund_address")
        private String insuranceFundAddress;

        @JsonProperty("liquidation_fee")
        private String liquidationFee;

        @JsonProperty("oracle_address")
        private String oracleAddress;

        @JsonProperty("symbol")
        private String symbol;
    }

    static class MarginParams {
        @JsonProperty("imf_base")
        private BigDecimal imfBase;

        @JsonProperty("imf_factor")
        private BigDecimal imfFactor;

        @JsonProperty("imf_shift")
        private BigDecimal imfShift;

        @JsonProperty("mmf_factor")
        private BigDecimal mmfFactor;
    }

    static class OptionMarginParams {
        @JsonProperty("imf")
        private MarginFactors imf;

        @JsonProperty("mmf")
        private MarginFactors mmf;
    }

    static class MarginFactors {
        @JsonProperty("long_itm")
        private String longItm;

        @JsonProperty("premium_multiplier")
        private String premiumMultiplier;

        @JsonProperty("short_itm")
        private String shortItm;

        @JsonProperty("short_otm")
        private String shortOtm;

        @JsonProperty("short_put_cap")
        private String shortPutCap;
    }
}
