package trade.wayruha.paradex.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import trade.wayruha.paradex.ParadexConfig;

import java.io.IOException;

public class JwtInterceptor implements Interceptor {
    private final ParadexConfig config;

    public JwtInterceptor(ParadexConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();

        // Add Authorization header if the token is available
        final Request.Builder requestBuilder = originalRequest.newBuilder();
        final String jwtToken = config.getJwtToken();
        if (jwtToken != null && !jwtToken.isEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer " + jwtToken);
        }

        return chain.proceed(requestBuilder.build());
    }
}