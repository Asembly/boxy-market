package asembly.exception.product;

public class ProductNotFoundException extends ProductException {

    public ProductNotFoundException() {
        super("Cart not found.");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
