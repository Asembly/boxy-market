package asembly.client.webclient;

import asembly.client.dto.FileUploadDto;
import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductWebClient {

    private final WebClient webClient;

    @Autowired
    public ProductWebClient(WebClient webClient)
    {
        this.webClient = webClient;
    }

    public ProductResponse createProduct(ProductCreateDto dto, List<FileUploadDto> files)
    {

        MultipartBodyBuilder multipart = new MultipartBodyBuilder();

        multipart.part("data", dto);

        for(var file: files)
        {
            ByteArrayResource resource = new ByteArrayResource(file.content()){
                @Override
                public @Nullable String getFilename() {
                    return file.filename();
                }
            };

            multipart.part("images", resource);
        }

        return webClient
                .post()
                .uri("/product-service/")
                .body(BodyInserters.fromMultipartData(multipart.build()))
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

}
