package trade.wayruha.paradex;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;
import lombok.NoArgsConstructor;
import trade.wayruha.paradex.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Objects;

import static trade.wayruha.paradex.config.Constant.HTTP_CLIENT_TIMEOUT_MS;

@Data
@NoArgsConstructor
public class ParadexConfig {
    public static final String TESTNET_HOST = "https://api.testnet.paradex.trade/v1/";

    private String host = TESTNET_HOST; // https://api.testnet.paradex.trade/v1 - testnet
    private String webSocketHost = "wss://ws.api.prod.paradex.trade/v1"; // replace with the actual WebSocket host
    private String privateKey = ""; // can be found on paradex page
    private String publicKey = ""; // can be found on paradex page
    private String jwtToken;

    public ParadexConfig(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * Host connection timeout.
     */
    private long httpConnectTimeout = HTTP_CLIENT_TIMEOUT_MS;
    /**
     * The host reads the information timeout.
     */
    private long httpReadTimeout = HTTP_CLIENT_TIMEOUT_MS;
    /**
     * The host writes the information timeout.
     */
    private long httpWriteTimeout = HTTP_CLIENT_TIMEOUT_MS;
    /**
     * Retry on failed connection, default true.
     */
    private boolean retryOnConnectionFailure = true;
    /**
     * Should we log request's data?
     */
    private boolean httpLogRequestData = false;

    /**
     * WebSocket should try re-connecting on fail forever
     */
    private boolean webSocketReconnectAlways = false;
    /**
     * If not forever, then how many times?
     */
    private int webSocketMaxReconnectAttempts = 3;
    private int webSocketPingIntervalSec = 45;

    private ObjectMapper objectMapper = createObjectMapper();

    /**
     * Can be found in Paradex system config https://app.paradex.trade/system-config
     * <p>
     * Mainnet - PRIVATE_SN_PARACLEAR_MAINNET
     * </p>
     * <p>
     * Testnet - PRIVATE_SN_POTC_SEPOLIA
     * </p>
     */
    public String getChainId() {
        Objects.requireNonNull(host);
        return TESTNET_HOST.equalsIgnoreCase(host) ? "PRIVATE_SN_POTC_SEPOLIA" : "PRIVATE_SN_PARACLEAR_MAINNET";
    }

    private static ObjectMapper createObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        mapper.registerModule(module);
        return mapper;
    }
}