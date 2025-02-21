package trade.wayruha.paradex.exception;

import lombok.Value;

@Value
public class WSException extends RuntimeException {
    int code;
    String text;

    public WSException(int code, String description) {
        super(code + ":" + description);
        this.code = code;
        this.text = description;
    }
}
