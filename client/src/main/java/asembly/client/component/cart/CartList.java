package asembly.client.component.cart;

import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class CartList extends VerticalLayout {

    private final ImageService imageService;

    public CartList(
            List<ProductResponse> products,
            ImageService imageService
    )
    {
        this.imageService = imageService;
        setPadding(true);
        setWidthFull();
        setHeightFull();

        Scroller scroller = new Scroller();
        VerticalLayout layout = new VerticalLayout();
        scroller.setContent(layout);

        layout.setMaxWidth("50vw");
        scroller.setMaxHeight("60vh");


        products.forEach(item -> {
            layout.add(new CartItem(item,imageService));
        });


        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(scroller);
    }

}
