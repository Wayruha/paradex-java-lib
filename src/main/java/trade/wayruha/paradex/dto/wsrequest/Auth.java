package trade.wayruha.paradex.dto.wsrequest;

public class Auth extends WSRequest {
    public Auth(String token) {
        super("auth", new TokenHolder(token));
    }
}
