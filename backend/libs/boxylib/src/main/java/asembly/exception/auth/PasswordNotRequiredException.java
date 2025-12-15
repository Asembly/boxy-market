package asembly.exception.auth;

public class PasswordNotRequiredException extends BusinessException{
    public PasswordNotRequiredException() {
        super(
                "Password not required",
                StatusCodeException.PASSWORD_NOT_REQUIRED
        );
    }
}
