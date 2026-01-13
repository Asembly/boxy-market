package asembly.client.layout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AppLayoutNavbar extends AppLayout {
    public AppLayoutNavbar() {
        FlexLayout layout = new FlexLayout();

        layout.setWidthFull();

        layout.getStyle().set("padding", "0px 25px");

        Span header = new Span(new H2("boxy market"));
        header.getStyle().setCursor("pointer");
        header.addClickListener(event -> {
            UI.getCurrent().navigate("/");
        });
        Div start = new Div(header);

        Button sell = new Button("Продать");
        sell.setThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sell.addClickListener(event -> {
            UI.getCurrent().navigate("/create-product");
        });

        Icon cartIcon = new Icon(VaadinIcon.CART);
        cartIcon.setSize("25px");
        Button cartButton = new Button(cartIcon);
        cartButton.getStyle().set("border", "none")
                .set("outline", "none")
                .setBackgroundColor("rgba(255,255,255,0");
        cartButton.addClickListener(event -> {
            UI.getCurrent().navigate("/cart");
        });

        HorizontalLayout end = new HorizontalLayout(sell, cartButton);
        end.setPadding(true);


        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.add(start);
        layout.add(end);

        addToNavbar(layout);
    }
}
