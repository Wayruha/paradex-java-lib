package trade.wayruha.paradex.exception;

import lombok.Data;
import lombok.Value;

@Value
public class WSException extends RuntimeException {
    int requestId;
    int code;
    Payload payload;

    public WSException(int requestId, int code, Payload payload) {
        super(code + ":" + payload);
        this.requestId = requestId;
        this.code = code;
        this.payload = payload;
    }

    @Data
    public static class Payload {
        int code;
        String message;
    }
}
