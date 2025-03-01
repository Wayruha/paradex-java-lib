package trade.wayruha.paradex.service;

import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.dto.response.MarketBboResponse;
import trade.wayruha.paradex.dto.response.MarketSummaryResponse;
import trade.wayruha.paradex.dto.response.MarketsStaticDataResponse;
import trade.wayruha.paradex.service.endpoint.MarketEndpoints;

public class MarketService extends ServiceBase{
    private final MarketEndpoints marketApi;

    public MarketService(ParadexConfig config) {
        super(config);
        this.marketApi = createService(MarketEndpoints.class);
    }

    /**
     * Get the best bid/ask for the given market.
     * <p>
     * BBO - Best Bid and Offer
     * </p>
     * @param market the market for which to retrieve the best bid/ask e.g. "ETC-USD-PERP"
     * @return MarketBboResponse
     */
    public MarketBboResponse getMarketBbo(String market) {
        return client.executeSync(marketApi.marketBidAsk(market));
    }

    /**
     * Returns all available information for all markets
     */
    public MarketSummaryResponse getMarketSummary(){
        return client.executeSync(marketApi.marketSummary());
    }

    /**
     * Returns all available information for given market
     * @param market e.g. "BTC-USD-PERP"
     */
    public MarketSummaryResponse getMarketSummary(String market){
        return client.executeSync(marketApi.marketSummary(market));
    }

    public MarketsStaticDataResponse getMarketsStaticData() {
        return client.executeSync(marketApi.marketsStaticData());
    }

    /**
     * @param market e.g. BTC-USD-PERP
     */
    public MarketsStaticDataResponse getMarketsStaticData(String market) {
        return client.executeSync(marketApi.marketsStaticData(market));
    }
}
