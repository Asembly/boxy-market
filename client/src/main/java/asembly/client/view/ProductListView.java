package asembly.client.view;

import asembly.client.component.ProductFactory;
import asembly.client.feign.ProductClient;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/product-list")
public class ProductListView extends VerticalLayout {

    private final ProductClient productClient;

    private final ProductFactory productFactory;

    @Autowired
    public ProductListView(
            ProductClient productClient,
            ProductFactory productFactory
    )
    {
        this.productClient = productClient;
        this.productFactory = productFactory;

        list();
    }

    private void list() {
        var products = productClient.getProducts().getBody();

        HorizontalLayout container = new HorizontalLayout();

        for(var product : products)
        {
            container.add(productFactory.createProductCart(product));
        }
        add(container);
    }



}
