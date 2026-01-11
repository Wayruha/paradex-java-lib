package trade.wayruha.paradex.dto.wsrequest;

import lombok.Data;
import lombok.Value;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public abstract class WSRequest {
    private static AtomicInteger GLOBAL_ID = new AtomicInteger(0);
    private final String jsonrpc = "2.0";
    private final String method;
    private final Object params;
    private int id;

    public WSRequest(String method, Object params) {
        this.method = method;
        this.params = params;
        this.id = GLOBAL_ID.incrementAndGet();
    }

    @Value
    public static class TokenHolder {
        String bearer;
    }

    @Value
    public static class Channel {
        String channel;
    }
}