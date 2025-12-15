package asembly.exception.auth;

public class ChatNotFoundException extends BusinessException {
    public ChatNotFoundException() {
        super(
                "Chat not found",
                StatusCodeException.CHAT_NOT_FOUND
        );
    }
}
