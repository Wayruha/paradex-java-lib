package trade.wayruha.paradex;

import trade.wayruha.paradex.service.MarketService;

public class MarketEndpointsTest {

    private final static String MARKET_PAIR = "BTC-USD-PERP";
    private static MarketService marketService;
    public static void main(String[] args) {
        final ParadexConfig config = new ParadexConfig();
        marketService = new MarketService(config);
        testGetMarketBbo();
        testMarketSummary();
        testOneMarketSummary();
    }

    private static void testGetMarketBbo(){
        System.out.println(marketService.getMarketBbo(MARKET_PAIR));
    }

    private static void testMarketSummary(){
        System.out.println(marketService.getMarketSummary());
    }

    private static void testOneMarketSummary(){
        System.out.println(marketService.getMarketSummary(MARKET_PAIR));
    }

    private static void testGetMarketsStaticData() { System.out.println(marketService.getMarketsStaticData()); }

    private static void testGetOneMarketStaticData() { System.out.println(marketService.getMarketsStaticData(MARKET_PAIR)); }
}
