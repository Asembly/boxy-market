package asembly.exception.storage;

public class StorageNotValidType extends StorageException {
    public StorageNotValidType(String message) {
        super(message);
    }

    public StorageNotValidType(String message, Throwable cause) {
        super(message,cause);
    }
}
