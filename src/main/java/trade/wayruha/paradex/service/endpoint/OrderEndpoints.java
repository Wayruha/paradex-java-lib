package trade.wayruha.paradex.service.endpoint;

import retrofit2.Call;
import retrofit2.http.*;
import trade.wayruha.paradex.dto.request.OrderCreateRequest;
import trade.wayruha.paradex.dto.response.AllOpenOrdersResponse;
import trade.wayruha.paradex.dto.response.AllPositionsResponse;
import trade.wayruha.paradex.dto.response.OrderDetailsResponse;

public interface OrderEndpoints {

    @GET("orders")
    Call<AllOpenOrdersResponse> getAllOpenOrders();

    @GET("positions")
    Call<AllPositionsResponse> getAllPositions();

    @POST("orders")
    Call<OrderDetailsResponse> createOrder(@Body OrderCreateRequest orderRequest);

    @GET("orders/{order_id}")
    Call<OrderDetailsResponse> getOrderDetailsById(@Path("order_id") String orderId);

    @DELETE("orders/{order_id}")
    Call<Void> cancelOrderById(@Path("order_id") String orderId);
}
