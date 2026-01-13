package asembly.client.view;

import asembly.client.component.product.ProductCard;
import asembly.client.feign.ProductClient;
import asembly.client.feign.StorageClient;
import asembly.client.layout.AppLayoutNavbar;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route("/product")
public class ProductView extends Div implements HasUrlParameter<String> {

    private final ProductClient productClient;
    private final StorageClient storageClient;

    private ProductResponse productResponse;
    private ProductCard productCard;

    public ProductView(ProductClient productClient, StorageClient storageClient)
    {
        this.productClient = productClient;
        this.storageClient = storageClient;
        add(new Div(new AppLayoutNavbar()));
        setWidthFull();
        setHeight("100%");
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String product_id) {
        loadProduct(product_id);
    }

    private void loadProduct(String product_id)
    {
    }
}
