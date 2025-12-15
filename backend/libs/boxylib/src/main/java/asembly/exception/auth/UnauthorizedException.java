package asembly.exception.auth;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super(
                "Unauthorized service",
                StatusCodeException.UNAUTHORIZED_SERVICE
        );    }
}
