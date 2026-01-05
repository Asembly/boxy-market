package asembly.client.view;

import asembly.client.feign.AuthClient;
import asembly.dto.auth.AuthRequest;
import asembly.dto.auth.AuthResponse;
import asembly.dto.user.UserResponse;
import com.vaadin.copilot.shaded.checkerframework.checker.units.qual.A;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Route("/register")
public class RegisterView extends VerticalLayout {

    private final AuthClient authClient;

    private final TextField usernameField = new TextField("Username");
    private final PasswordField passwordField = new PasswordField("Password");
    private final Button submitButton = new Button("Register");
    private final BeanValidationBinder<AuthRequest> binder = new BeanValidationBinder<>(AuthRequest.class);

    public RegisterView(AuthClient authClient)
    {
        this.authClient = authClient;
        setupLayout();
        setupButtons();
    }

    private void setupLayout()
    {
        H1 header = new H1("Create Account");

        FormLayout form = new FormLayout();
        form.add(new VerticalLayout(usernameField, passwordField));

        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        RouterLink loginLink = new RouterLink("Already have an account? Login", UserListView.class);

        add(header, form, submitButton, loginLink);
    }

    private void setupButtons()
    {
        submitButton.addClickListener(event -> register());
        binder.addStatusChangeListener(event -> submitButton.setEnabled(binder.isValid()));
    }

    private void register()
    {
        AuthRequest request = new AuthRequest(usernameField.getValue(), passwordField.getValue());
        try{
            submitButton.setEnabled(false);
            submitButton.setText("Registering...");

            ResponseEntity<?> response = authClient.signUp(request);

            if(response.getStatusCode() == HttpStatus.OK && response.getBody() != null)
            {
                Notification.show("Registration successful! Welcome, ")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                UI.getCurrent().navigate("/");
            }
        } finally {
            submitButton.setEnabled(true);
            submitButton.setText("Register");
        }

    }

}
