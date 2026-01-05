package asembly.client.view;

import asembly.client.component.ProductFactory;
import asembly.client.feign.CartClient;
import asembly.client.feign.ProductClient;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/cart")
public class CartView extends VerticalLayout {

    private final CartClient cartClient;
    private final ProductClient productClient;
    private final ProductFactory productFactory;

    @Autowired
    public CartView(
            CartClient cartClient,
            ProductClient productClient,
            ProductFactory productFactory
            )
    {
        this.cartClient = cartClient;
        this.productClient = productClient;
        this.productFactory = productFactory;

        list();
    }

    private void list() {
        var cart = cartClient.getCartByUserId("1eff8d70").getBody();

        HorizontalLayout container = new HorizontalLayout();

        for(var product_id : cart.products_id())
        {
            var product = productClient.getProductById(product_id).getBody();
            container.add(productFactory.createProduct(product));
        }
        add(container);
    }

}
