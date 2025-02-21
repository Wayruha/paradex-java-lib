package trade.wayruha.paradex.dto.wsrequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString(callSuper = true)
@Getter
@Setter
public class Subscription extends WSRequest {
    public Subscription(String channel) {
        super("subscribe", new Channel(channel));
    }
}
