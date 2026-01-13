package asembly.client.component.event;

import asembly.client.component.product.ProductCard;
import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;

@Getter
public class RemoveCartEvent extends ComponentEvent<ProductCard> {

    private final String product_id;

    public RemoveCartEvent(ProductCard source, String product_id) {
        super(source, false);
        this.product_id = product_id;
    }
}
