package asembly.exception.auth;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException(String tokenName) {
        super(
                tokenName + " token is expired",
                StatusCodeException.UNAUTHORIZED_SERVICE
        );    }
}
