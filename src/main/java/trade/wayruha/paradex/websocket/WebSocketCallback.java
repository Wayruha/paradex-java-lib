package trade.wayruha.paradex.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.Response;

public interface WebSocketCallback<T> {
    TypeReference<Object> ORDER_BOOK_UPDATE_TYPE = new TypeReference<>() {};

    /**
     * Be called when the request successful.
     */
    void onResponse(T response);

    TypeReference<T> getType();

    default void onClosed(int code, String reason){

    }

    default void onFailure(Throwable ex, Response response){

    }

    default void onOpen(Response response){

    }
}
