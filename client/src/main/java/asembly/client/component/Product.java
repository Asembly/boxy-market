package asembly.client.view;

import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Product extends VerticalLayout {


    private final ImageService imageService;

    private final String title;
    private final String description;
    private final Integer price;

    private final H2 titleField;
    private final Text descriptionField;
    private final Text priceField;

    private final Div productContainer;

    private final ProductResponse product;

    public Product(
            ProductResponse product,
            ImageService imageService
    )
    {
        this.imageService = imageService;

        title = product.title();
        description = product.description();
        price = product.price();

        titleField = new H2(title);
        descriptionField = new Text(description);
        priceField = new Text(price.toString() + "₽");

        productContainer = new Div();

        this.product = product;

        init();
    }

    private void init()
    {
        productContainer.add(titleField);
        productContainer.add(descriptionField);
        productContainer.add(
                imageService.loadImage(product.photos())
        );
        productContainer.add(priceField);
    }
}
