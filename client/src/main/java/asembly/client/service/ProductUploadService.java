package asembly.client.service;

import asembly.client.dto.UploadedFile;
import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Service
public class ProductUploadService {

    private final WebClient client;

    public ProductUploadService(
            WebClient.Builder builder,
            @Value("${feign.services}") String url)
    {
        client = builder
                .baseUrl(url)
                .build();

        log.info("URL: {}", url);
    }

    public ProductResponse createProduct(ProductCreateDto dto, List<UploadedFile> images)
    {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder
                .part("data", dto)
                .header("Content-Type", "application/json");

        for(int i = 0; i < images.size(); i++)
        {
            UploadedFile image = images.get(i);
            ByteArrayResource resource = new ByteArrayResource(image.content())
            {
                @Override
                public @Nullable String getFilename() {
                    return image.filename();
                }
            };

            builder.part("images", resource)
                    .header("Content-Type", image.contentType());

        }

        log.info("Builder: {}", builder);

        return client.post()
                .uri("/product-service/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

}
