package asembly.client.view;

import asembly.client.component.product.CatalogList;
import asembly.client.feign.ProductClient;
import asembly.client.layout.AppLayoutNavbar;
import asembly.client.service.ImageService;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Route(value = "/", layout = AppLayoutNavbar.class)
public class CatalogView extends VerticalLayout {

    private final ProductClient productClient;
    private final ImageService imageService;

    public CatalogView(ProductClient productClient, ImageService imageService)
    {
        this.productClient = productClient;
        this.imageService = imageService;
        setHeightFull();
        setWidthFull();

        FlexLayout layout = new FlexLayout();

        var products = productClient.getProducts().getBody();
        for(var product: productClient.getProducts().getBody())
        {
            products.add(product);
        }

        CatalogList list = new CatalogList(
                products,
                imageService
        );

        layout.add(list);
        layout.setWidth("50%");

//        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        add(layout);
    }
}
