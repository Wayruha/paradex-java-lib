package trade.wayruha.paradex.dto.wsresponse;

import trade.wayruha.paradex.dto.wsrequest.Subscription;

public class Response {
    private String jsonrpc;
    private long usIn;
    private long usDiff;
    private long id;

    public static class SubscriptionConfirmation extends Response {
        private Subscription.Channel result;
    }
}
