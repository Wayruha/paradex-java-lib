package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.OrderSide;
import trade.wayruha.paradex.dto.TimeInForceType;
import trade.wayruha.paradex.dto.request.PlaceOrderAction;
import trade.wayruha.paradex.dto.response.OrderResponseDetails;
import trade.wayruha.paradex.service.ExchangeService;

import java.math.BigDecimal;
import java.util.List;

public class PlaceOrderTest {

  public static void main(String[] args) {
    //testOrderPlacement
    final String walletPrivateKey = "";
    final ParadexConfig config = new ParadexConfig(null, walletPrivateKey);
    final ExchangeService service = new ExchangeService(config);
    final PlaceOrderAction orderAction = new PlaceOrderAction();
    orderAction.addLimitOrder(1, OrderSide.BID, new BigDecimal(3000), new BigDecimal("0.005"), TimeInForceType.GTC);
    List<OrderResponseDetails> resp = service.placeOrder(orderAction);
    System.out.println(resp);
  }
}
