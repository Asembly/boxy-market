package asembly.client.component.product;

import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.dom.Style;

public class ProductCard extends Card {

    public ProductCard(
            ProductResponse product,
            ImageService imageService
    )
    {

        setWidthFull();
        setHeightFull();

        Image image = new Image();
        image.setSrc(imageService.getUrl(product.photos().get(0)));
        image.setHeight("120px");
        image.setWidthFull();

        Div description = new Div(new Text(product.description()));
        description.setMaxHeight("50%");
        description.getStyle().setOverflow(Style.Overflow.HIDDEN);


        setMedia(image);
        setTitle(product.title());
        setHeaderSuffix(new Div(product.price().toString() + "₽"));
        add(description);
    }
}

