package asembly.client.view;

import asembly.client.dto.UploadedFile;
import asembly.client.feign.ProductClient;
import asembly.client.service.ProductUploadService;
import asembly.dto.auth.AuthRequest;
import asembly.dto.product.ProductCreateDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.InMemoryUploadHandler;
import com.vaadin.flow.server.streams.UploadHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Route("/product")
public class CreateProductView extends VerticalLayout {

    private final ProductClient productClient;


    private final TextField titleField = new TextField("Заголовок");
    private final TextField descriptionField = new TextField("Описание");
    private final NumberField priceField = new NumberField("Цена");
    private final Button submitButton = new Button("Опубликовать");
    private final BeanValidationBinder<AuthRequest> binder = new BeanValidationBinder<>(AuthRequest.class);

    private Upload fileField;
    private final List<UploadedFile> uploadedFiles = new ArrayList<>();

    @Autowired
    private ProductUploadService productUploadService;

    @Autowired
    public CreateProductView(ProductClient productClient)
    {
        this.productClient = productClient;

        setupLayout();
        setupButtons();
    }

    private void setupLayout()
    {
        FormLayout form = new FormLayout(new VerticalLayout(
                titleField,
                descriptionField,
                priceField
        ));

        InMemoryUploadHandler inMemoryHandler = UploadHandler
                .inMemory((metadata, data) -> {

                    String fileName = metadata.fileName();
                    String mimeType = metadata.contentType();
                    long contentLength = metadata.contentLength();

                    log.info("Data files: {}",data);

                    var uploaded = new UploadedFile(data, fileName, mimeType);
                    uploadedFiles.add(uploaded);

                    log.info("Uploaded files: {}",uploaded);
                });

        fileField = new Upload(inMemoryHandler);
        log.info("File field: {}", fileField);

        add(form, fileField, submitButton);

    }

    private void setupButtons()
    {
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(event -> createProduct());
    }

    private void createProduct()
    {
        ProductCreateDto data = new ProductCreateDto(
                "1eff8d70",
                titleField.getValue(),
                descriptionField.getValue(),
                priceField.getValue().intValue()
                );
        log.info("Uploaded Files: {}",uploadedFiles);

        try{
            submitButton.setEnabled(false);

            var response = productUploadService.createProduct(data, uploadedFiles);
            log.info("RESPONSE: {}",response);
            if(response.id() != null)
                UI.getCurrent().navigate("/product-list");
        } finally {
            submitButton.setEnabled(true);
        }
    }
}
