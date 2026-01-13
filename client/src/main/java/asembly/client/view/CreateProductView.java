package asembly.client.view;

import asembly.client.component.product.ProductForm;
import asembly.client.feign.ProductClient;
import asembly.client.layout.AppLayoutNavbar;
import asembly.client.webclient.ProductWebClient;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "/create-product", layout = AppLayoutNavbar.class)
public class CreateProductView extends VerticalLayout {

    private final ProductClient productClient;
    private final ProductWebClient productWebClient;

    public CreateProductView(ProductClient productClient, ProductWebClient productWebClient)
    {
        this.productClient = productClient;
        this.productWebClient = productWebClient;

        setHeightFull();

        FlexLayout container = new FlexLayout();
        ProductForm productForm = new ProductForm(productWebClient);

        productForm.addCreateProductEvent(event -> {});

        container.setWidthFull();
        container.setHeightFull();
        container.add(new Div(productForm));
        container.setJustifyContentMode(JustifyContentMode.CENTER);
        container.setAlignItems(Alignment.CENTER);

        add(container);
    }

    public void init() {
    }


}
