package trade.wayruha.paradex;

import trade.wayruha.paradex.dto.request.CancelOrderAction;
import trade.wayruha.paradex.dto.response.CancellationStatus;
import trade.wayruha.paradex.service.ExchangeService;

import java.util.List;

public class CancelOrderTest {

  public static void main(String[] args) {
    //testOrderCancellation
    final String walletPrivateKey = "";
    final String orderId = "";
    final ParadexConfig config = new ParadexConfig(null, walletPrivateKey);
    final ExchangeService service = new ExchangeService(config);
    final CancelOrderAction cancelOrderAction = new CancelOrderAction();
    cancelOrderAction.addCancel(1, Long.parseLong(orderId));
    List<CancellationStatus> resp = service.cancelOrder(cancelOrderAction);
    System.out.println(resp);
  }
}
