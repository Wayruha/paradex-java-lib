package trade.wayruha.paradex.exception;

public class ParadexException extends RuntimeException {
  public ParadexException(String message) {
    super(message);
  }

  public ParadexException(String message, Throwable cause) {
    super(message, cause);
  }
}
