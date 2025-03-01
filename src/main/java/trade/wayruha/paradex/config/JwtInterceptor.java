package trade.wayruha.paradex.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import trade.wayruha.paradex.ParadexConfig;

import java.io.IOException;

//todo ми токен генеруємо на один день,правилно? відповідно десь буде відбуватися перегенерація.
// а от ця штука створються один раз при створенні ApiClient (вопрос,а звідки АпіКлієнт візьме токен?
// Чи нам треба один клієнт для того щоб відправити auth request а потім ми маємо створити інший для роботи?)
//якщо коротко - так не має бути. на крайняк сюди можна передавати конфіг,і в методі інтерсепт вже брати токен з конфігу.
// в результаті на момент ДО того як ми авторизуємося цей токен буде налл, а потім ми його підставимо.
// Але це нашвидкоруч придумане рішення, можливо є кращі підходи
public class JwtInterceptor implements Interceptor {
    private final ParadexConfig config;

    public JwtInterceptor(ParadexConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // Add Authorization header if the token is available
        Request.Builder requestBuilder = originalRequest.newBuilder();
        if (config.getJwtToken() != null && !config.getJwtToken().isEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer " + config.getJwtToken());
        }

        return chain.proceed(requestBuilder.build());
    }
}