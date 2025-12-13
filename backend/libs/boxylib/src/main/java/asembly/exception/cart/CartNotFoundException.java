package asembly.exception.cart;

public class CartNotFoundException extends CartException {

    public CartNotFoundException() {
        super("Cart not found.");
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
