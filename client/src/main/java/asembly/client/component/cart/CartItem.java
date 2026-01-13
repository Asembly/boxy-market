package asembly.client.component.cart;

import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CartItem extends HorizontalLayout {

    public CartItem(
            ProductResponse product,
            ImageService imageService)
    {
        Image image = new Image();
        image.setSrc(imageService.getUrl(product.photos().get(0)));
        image.setHeight("50px");
        image.setWidth("50px");

        Text title = new Text(product.title());
        Text description = new Text(product.description());
        Text price = new Text(product.price().toString() + "₽");

        Icon trashIcon = VaadinIcon.TRASH.create();
        trashIcon.setSize("20px");

        Button removeButton = new Button(trashIcon);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(removeButton);

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.add(new Div(title), new Div(description), buttonsLayout);

        HorizontalLayout priceLayout = new HorizontalLayout();
        priceLayout.add(new Div(price));

        setAlignItems(Alignment.BASELINE);
        setWidthFull();
        add(image, infoLayout, priceLayout);
    }
}
