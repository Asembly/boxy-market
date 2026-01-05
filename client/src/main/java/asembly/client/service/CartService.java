package asembly.client.service;

import asembly.client.feign.CartClient;
import com.vaadin.flow.component.button.Button;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartClient cartClient;

    public Button addToCartButton(String product_id)
    {
        Button addToCart = new Button("Add to Cart");
        addToCart.addClickListener(event -> {
            cartClient.addProduct("1eff8d70", product_id);
        });
        return addToCart;
    }

}
