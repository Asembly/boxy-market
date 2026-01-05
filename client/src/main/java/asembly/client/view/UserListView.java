package asembly.client.view;

import asembly.client.feign.ProductClient;
import asembly.client.feign.UserClient;
import asembly.dto.product.ProductResponse;
import asembly.dto.user.UserResponse;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/user-list")
public class UserListView extends VerticalLayout {

    private final UserClient userClient;
    private final Grid<UserResponse> grid = new Grid<>(UserResponse.class);;

    @Autowired
    public UserListView(UserClient userClient)
    {
        this.userClient = userClient;
        add(grid);
        listUsers();
    }

    private void listUsers() {
        grid.setItems(userClient.getUsers().getBody());
    }

}
