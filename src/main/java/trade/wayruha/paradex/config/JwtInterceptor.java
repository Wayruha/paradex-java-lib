package trade.wayruha.paradex.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class JwtInterceptor implements Interceptor {
    private final String token;

    public JwtInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // Add Authorization header if the token is available
        Request.Builder requestBuilder = originalRequest.newBuilder();
        if (token != null && !token.isEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        return chain.proceed(requestBuilder.build());
    }
}