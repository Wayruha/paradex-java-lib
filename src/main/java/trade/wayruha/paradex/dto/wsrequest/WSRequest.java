package trade.wayruha.paradex.dto.wsrequest;

import lombok.Data;
import lombok.Value;

@Data
public abstract class WSRequest {
    private final String jsonrpc = "2.0";
    private final String method;
    private final Object params;
    private int id;

    public WSRequest(String method, Object params) {
        this.method = method;
        this.params = params;
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