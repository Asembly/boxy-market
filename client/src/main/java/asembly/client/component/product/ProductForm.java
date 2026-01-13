package asembly.client.component.product;

import asembly.client.component.event.CreateProductEvent;
import asembly.client.dto.FileUploadDto;
import asembly.client.webclient.ProductWebClient;
import asembly.dto.product.ProductCreateDto;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.server.streams.InMemoryUploadHandler;
import com.vaadin.flow.server.streams.UploadHandler;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public class ProductForm extends FormLayout {

    private final TextField title = new TextField("Заголовок");
    private final TextField description = new TextField("Описание");
    private final TextField price = new TextField("Цена");
    private final Button submit = new Button("Создать товар");
    private final List<FileUploadDto> uploadedFiles = new ArrayList<>();
    private final ProductWebClient productClient;

    public ProductForm(ProductWebClient productClient)
    {
        this.productClient = productClient;
        InMemoryUploadHandler inMemoryHandler = UploadHandler
                .inMemory((metadata, data) -> {
                    String fileName = metadata.fileName();
                    String mimeType = metadata.contentType();
                    long contentLength = metadata.contentLength();

                    uploadedFiles.add(new FileUploadDto(data, mimeType, fileName));

                });
        Upload images = new Upload(inMemoryHandler);

        setAutoResponsive(true);
        buttonConfigure();

        addFormRow(title);
        addFormRow(description);
        addFormRow(price);
        addFormRow(images);
        addFormRow(submit);

    }

    public Registration addCreateProductEvent(
            ComponentEventListener<CreateProductEvent> listener
    )
    {
        return addListener(CreateProductEvent.class, listener);
    }

    public void buttonConfigure()
    {
        submit.setWidthFull();
        submit.setThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submit.addClickListener(event -> {
            fireEvent(new CreateProductEvent(
                    this,
                    new ProductCreateDto(
                            "1eff8d70",
                            title.getValue(),
                            description.getValue(),
                            Integer.parseInt(price.getValue())
                    ),
                    uploadedFiles,
                    productClient
            ));
        });
    }

}
