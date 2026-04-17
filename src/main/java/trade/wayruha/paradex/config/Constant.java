package trade.wayruha.paradex.config;

public class Constant {
    /**
     * The default timeout is 30 seconds.
     */
    public static final long HTTP_CLIENT_TIMEOUT_MS = 30 * 1000;

    /**
     * max allowed discrepancy in nonce (i.e., timestamp, millis)
     */
    public static final int DEFAULT_RECEIVING_WINDOW = 60_000;
    public static final String WEBSOCKET_INTERRUPTED_EXCEPTION = "The server terminated the connection for an unknown reason";
    public static final String API_CLIENT_ERROR_MESSAGE_PARSE_EXCEPTION = "Can't parse error message";
}
