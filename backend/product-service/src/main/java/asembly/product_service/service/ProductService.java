package asembly.product_service.service;

import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductResponse;
import asembly.dto.product.ProductUpdateDto;
import asembly.product_service.client.StorageClient;
import asembly.product_service.entity.Product;
import asembly.product_service.mapper.ProductMapper;
import asembly.product_service.repository.ProductRepository;
import asembly.utils.GeneratorId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageClient storageClient;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ResponseEntity<List<ProductResponse>> getProducts(){
        var products = productRepository.findAll();
        log.info("FindAll Products:{}", products);
        var productsResponse = products.stream().map(productMapper::productToProductResponse).toList();
        return ResponseEntity.ok(productsResponse);
    }

    public ResponseEntity<ProductResponse> getProductById(String id){

        var product = productRepository.findById(id).orElseThrow(
                //todo UserNotFound::new
        );

        log.info("FindById Product:{}", product);

        var productResponse = productMapper.productToProductResponse(product);
        return ResponseEntity.ok(productResponse);
    }

    public ResponseEntity<List<ProductResponse>> getProductsByUserId(String user_id){
        var products = productRepository.findByUserId(user_id).orElseThrow(
                //todo UserNotFound::new
        );

        log.debug("FindByUserId Product:{}", products);

        var productsResponse = products.stream().map(productMapper::productToProductResponse).toList();
        return ResponseEntity.ok(productsResponse);
    }

    public ResponseEntity<ProductResponse> createProduct(ProductCreateDto dto, MultipartFile[] images){

        //todo проверка user_id т.е делать обращение к UserService через feign client

        log.info("Files: {}", images.length);

        List<String> filenames = new ArrayList<>(List.of());

        for(var image: images)
             filenames.add(storageClient.uploadImageFile(image).getBody());

        var product = Product.builder()
                .id(GeneratorId.generateId())
                .user_id(dto.user_id())
                .sale(0.0f)
                .title(dto.title())
                .description(dto.description())
                .discontinued(false)
                .price(dto.price())
                .photos(filenames)
                .created_at(LocalDateTime.now())
                .build();

        var save = productRepository.save(product);

        log.info("CreateProduct product:{}", save);

        var productResponse = productMapper.productToProductResponse(save);

        log.info("CreateProduct productResponse:{}", productResponse);

        return ResponseEntity.ok(productResponse);
    }

    public ResponseEntity<ProductResponse> deleteProduct(String id){

        var product = productRepository.findById(id).orElseThrow(
                //todo ProductNotFound::new
        );

        productRepository.delete(product);

        var productResponse = productMapper.productToProductResponse(product);

        return ResponseEntity.ok(productResponse);
    }

    public ResponseEntity<List<String>> getProductPhotosUrl(String id)
    {
        var product = productRepository.findById(id).orElseThrow(
                //todo ProductNotFound::new
        );

        log.info("Product: {}", product);

        List<String> urls = new ArrayList<>(List.of());

        for(var filename: product.getPhotos())
        {
            log.info("filename: {}", filename);
            urls.add(storageClient.getUrl(filename).getBody());
        }

        log.info("Urls: {}", urls.toString());

        if(urls.isEmpty())
        {
            //todo throw new FileNotFound();
        }

        return ResponseEntity.ok(urls);
    }

    public ResponseEntity<ProductResponse> updateProduct(ProductUpdateDto dto, String id){

        var product = productRepository.findById(id).orElseThrow(
                //todo ProductNotFound::new
        );

        if(dto.title() != null)
            product.setTitle(dto.title());

        if(dto.description() != null)
            product.setDescription(dto.description());

        if(dto.price() != null)
            product.setPrice(dto.price());

        if(dto.sale() != null)
            product.setSale(dto.sale());

        if(dto.discontinued() != null)
            product.setDiscontinued(dto.discontinued());

        var save = productRepository.save(product);

        var productResponse = productMapper.productToProductResponse(save);

        return ResponseEntity.ok(productResponse);
    }

}
