package asembly.client.component.event;

import asembly.client.component.product.ProductForm;
import asembly.client.dto.FileUploadDto;
import asembly.client.webclient.ProductWebClient;
import asembly.dto.product.ProductCreateDto;
import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductEvent extends ComponentEvent<ProductForm> {

    private final ProductWebClient productClient;

    public CreateProductEvent(
            ProductForm source,
            ProductCreateDto productCreateDto,
            List<FileUploadDto> uploadedFiles,
            ProductWebClient productClient
    ) {
        super(source, false);
        this.productClient = productClient;
        productClient.createProduct(productCreateDto, uploadedFiles);
    }
}
