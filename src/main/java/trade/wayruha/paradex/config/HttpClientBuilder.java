package trade.wayruha.paradex.config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import trade.wayruha.paradex.ParadexConfig;

import java.util.concurrent.TimeUnit;

public class HttpClientBuilder {
  private final ParadexConfig config;
  private final HttpLoggingInterceptor loggingInterceptor;

  public HttpClientBuilder(final ParadexConfig config) {
    this.config = config;
    final HttpLoggingInterceptor logInterceptor = getDefaultLoggingInterceptor();
    this.loggingInterceptor = logInterceptor;
  }

  public HttpClientBuilder(final ParadexConfig config, HttpLoggingInterceptor loggingInterceptor) {
    this.config = config;
    this.loggingInterceptor = loggingInterceptor;
  }

  public OkHttpClient buildClient() {
    final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    clientBuilder.connectTimeout(this.config.getHttpConnectTimeout(), TimeUnit.MILLISECONDS)
        .readTimeout(this.config.getHttpReadTimeout(), TimeUnit.MILLISECONDS)
        .writeTimeout(this.config.getHttpWriteTimeout(), TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(this.config.isRetryOnConnectionFailure());
    if (this.config.isHttpLogRequestData() && loggingInterceptor != null) {
      clientBuilder.addInterceptor(loggingInterceptor);
    }
    return clientBuilder.build();
  }

  @NotNull
  private static HttpLoggingInterceptor getDefaultLoggingInterceptor() {
    final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new trade.wayruha.paradex.config.HttpClientLogger());
    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return logInterceptor;
  }
}
