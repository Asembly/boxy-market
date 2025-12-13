package asembly.event;

import asembly.event.types.ProductEventType;

public record ProductEvent(ProductEventType type, String product_id) {
}
