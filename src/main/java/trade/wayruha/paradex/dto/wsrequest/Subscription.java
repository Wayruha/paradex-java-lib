package trade.wayruha.paradex.dto.wsrequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Subscription extends WSRequest {
    @JsonIgnore
    private String channel;

    public Subscription(String channel) {
        super("subscribe", new Channel(channel));
        this.channel = channel;
    }

    @Override
    public String toString() {
        return channel;
    }
}
