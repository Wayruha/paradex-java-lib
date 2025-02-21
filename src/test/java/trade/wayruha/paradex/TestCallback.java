package trade.wayruha.paradex;

import com.fasterxml.jackson.core.type.TypeReference;
import trade.wayruha.paradex.websocket.WebSocketCallback;

class TestCallback<T> implements WebSocketCallback<T> {
    final TypeReference<T> type;

    public TestCallback(TypeReference<T> type) {
        this.type = type;
    }

    @Override
    public void onResponse(T response) {
        System.out.println(response);

    }

    @Override
    public TypeReference<T> getType() {
        return type;
    }
}
