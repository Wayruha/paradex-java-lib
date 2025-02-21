package trade.wayruha.paradex.websocket;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
  private static final AtomicInteger COUNTER = new AtomicInteger();

  public static int getNextId() {
    return COUNTER.addAndGet(1);
  }
}
