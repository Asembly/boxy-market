package asembly.exception.cart;

public class CartEmptyException extends CartException {

    public CartEmptyException() {
        super("Products not found.");
    }

    public CartEmptyException(String message) {
        super(message);
    }

    public CartEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
