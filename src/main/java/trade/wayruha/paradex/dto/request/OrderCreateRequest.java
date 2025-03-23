package trade.wayruha.paradex.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCreateRequest extends OrderParameters {
    /**
     * Order Payload signed with STARK Private Key
     */
    String signature;

    @JsonProperty("signature_timestamp")
    long timestamp;

    @JsonProperty("recv_window")
    int receiveWindow;

    public OrderCreateRequest(OrderParameters orderParameters, String signature, long timestamp, int receiveWindow) {
        super(
                orderParameters.getInstruction(),
                orderParameters.getMarket(),
                orderParameters.getPrice(),
                orderParameters.getSide(),
                orderParameters.getType(),
                orderParameters.getSize(),
                orderParameters.getClientId(),
                orderParameters.getFlags(),
                orderParameters.getSelfTradePrevention(),
                orderParameters.getTriggerPrice());
        this.signature = signature;
        this.timestamp = timestamp;
        this.receiveWindow = receiveWindow;
    }
}
