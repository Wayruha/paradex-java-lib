package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trade.wayruha.paradex.dto.response.MarketBboResponse;
import trade.wayruha.paradex.dto.response.MarketSummaryResponse;
import trade.wayruha.paradex.dto.response.MarketsStaticDataResponse;

public interface MarketEndpoints {

    @GET("bbo/{market}")
    Call<MarketBboResponse> marketBidAsk(@Path("market") String market);

    @GET("markets/summary?market=ALL")
    Call<MarketSummaryResponse> marketSummary();

    @GET("markets/summary")
    Call<MarketSummaryResponse> marketSummary(@Query("market") String market);

    @GET("markets")
    Call<MarketsStaticDataResponse> marketsStaticData();

    @GET("markets")
    Call<MarketsStaticDataResponse> marketsStaticData(@Query("market") String market);
}
