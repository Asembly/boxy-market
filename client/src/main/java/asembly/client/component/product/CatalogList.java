package asembly.client.component.product;

import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class CatalogList extends VerticalLayout {
    public CatalogList(
            List<ProductResponse> products,
            ImageService imageService
    )
    {


        setPadding(true);
        setWidthFull();
        setHeightFull();

        VerticalLayout layout = new VerticalLayout();
        layout.getStyle()
                .set("display", "grid")
                .set("grid-template-columns", "repeat(auto-fill, minmax(200px, 1fr))")
                .set("grid-auto-rows", "280px")
                .set("gap", "1.5rem")
                .set("padding", "1rem");

        products.forEach(item -> {
            layout.add(new ProductCard(item,imageService));
        });


        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(layout);
    }
}
