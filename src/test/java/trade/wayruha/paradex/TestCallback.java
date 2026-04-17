package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import trade.wayruha.paradex.websocket.WebSocketCallback;

class TestCallback<T> implements WebSocketCallback<T> {
    final TypeReference<T> type;
    final String prefix;

    public TestCallback(TypeReference<T> type) {
        this(type, "");
    }

    public TestCallback(TypeReference<T> type, String prefix) {
        this.type = type;
        this.prefix = prefix;
    }

    @Override
    public void onResponse(T response) {
        System.out.println(prefix + ": " + response);

    }

    @Override
    public TypeReference<T> getType() {
        return type;
    }
}
