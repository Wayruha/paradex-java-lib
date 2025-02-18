package trade.wayruha.paradex.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import trade.wayruha.paradex.ParadexConfig;
import trade.wayruha.paradex.config.ApiClient;

import static trade.wayruha.paradex.config.Constant.DEFAULT_RECEIVING_WINDOW;

public abstract class ServiceBase {
  protected int receivingWindow = DEFAULT_RECEIVING_WINDOW;
  protected final ApiClient client;

  public ServiceBase(ApiClient client) {
    this.client = client;
  }

  public ServiceBase(ParadexConfig config) {
    this(new ApiClient(config));
  }

  public  <T> T createService(Class<T> apiClass) {
    return client.createService(apiClass);
  }

  protected ObjectMapper getObjectMapper(){
    return client.getConfig().getObjectMapper();
  }

  protected long now() {
    return System.currentTimeMillis();
  }
}
