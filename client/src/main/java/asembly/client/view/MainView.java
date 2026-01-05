package asembly.client.view;

import asembly.client.feign.ProductClient;
import asembly.client.feign.UserClient;
import asembly.dto.product.ProductResponse;
import asembly.dto.user.UserResponse;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends AppLayout {

    @Autowired
    public MainView() {

        H1 logo = new H1("Marketplace Gateway");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM
        );
        var header = new HorizontalLayout(new DrawerToggle(), logo);

        createDrawer();
        addToNavbar(header);
    }

    private void createDrawer()
    {
        addToDrawer(new VerticalLayout(
                new RouterLink("Product List", ProductListView.class),
                new RouterLink("User List", UserListView.class),
                new RouterLink("Register", RegisterView.class)
        ));
    }
}
