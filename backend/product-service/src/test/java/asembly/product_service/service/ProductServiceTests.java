package asembly.product_service.service;

import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductUpdateDto;
import asembly.dto.user.UserResponse;
import asembly.product_service.client.StorageClient;
import asembly.product_service.client.UserClient;
import asembly.product_service.entity.Product;
import asembly.product_service.mapper.ProductMapper;
import asembly.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private StorageClient storageClient;

    @Mock
    private UserClient userClient;

    private Product product;
    private Product product2;
    private ProductCreateDto createDto;
    private ProductUpdateDto updateDto;
    private ProductMapper productMapper;
    private MultipartFile[] files;

    @BeforeEach
    public void setup()
    {
        productMapper = ProductMapper.INSTANCE;

        product2 = Product.builder()
                .id("2")
                .user_id("1")
                .title("Test title")
                .description("Test description")
                .price(1)
                .sale(0.0f)
                .photos(List.of("uuid"))
                .created_at(LocalDateTime.now())
                .discontinued(false)
                .build();

        files = new MultipartFile[] {
                new MockMultipartFile(
                        "file1",
                        "image1.jpg",
                        "image/jpeg",
                        "fake image content 1".getBytes()
                ),
                new MockMultipartFile(
                        "file2",
                        "image2.png",
                        "image/png",
                        "fake image content 2".getBytes()
                )
        };

        userResponse1 = UserResponse.builder()
                .id("1")
                .username("Asembly")
                .created_at(LocalDateTime.now())
                .build()
        ;

        product = Product.builder()
                .id("1")
                .user_id("1")
                .title("Test title")
                .description("Test description")
                .price(1)
                .sale(0.0f)
                .photos(List.of(
                        files[0].getOriginalFilename(),
                        files[1].getOriginalFilename()
                ))
                .created_at(LocalDateTime.now())
                .discontinued(false)
                .build();

        createDto = ProductCreateDto.builder()
                .user_id("1")
                .title("Test title")
                .description("Test description")
                .price(1)
                .build();

        updateDto = ProductUpdateDto.builder()
                .description("Test updated")
                .price(1111)
                .sale(11.11f)
                .discontinued(true)
                .build();

    }

    @Test
    public void productService_getProductsByUserId_returnsProductResponseWithCurrentUserId()
    {
        //Find by this id
        var user_id = "1";

        //Get Product by user_id
        Mockito.when(productRepository.findByUserId(user_id)).thenReturn(Optional.of(List.of(product, product2)));

        var findProducts = productService.getProductsByUserId(user_id).getBody();
        var productResponse = productMapper.productToProductResponse(product);

        Assertions.assertThat(findProducts).isNotNull();
        Assertions.assertThat(productResponse.user_id()).isEqualTo(findProducts.get(0).user_id());
        Assertions.assertThat(productResponse.user_id()).isEqualTo(findProducts.get(1).user_id());
    }

    @Test
    public void productService_updateProduct_returnUpdatedProductResponse()
    {
        //Find by this id
        var product_id = "1";

        Mockito.when(productRepository.findById(product_id)).thenReturn(Optional.of(product));

        var findProduct = productService.getProductById(product_id).getBody();

        Assertions.assertThat(findProduct).isNotNull();

        //Save update values and take again
        productService.updateProduct(updateDto, product_id);
        Mockito.when(productRepository.findById(product_id)).thenReturn(Optional.of(product));
        var findNewProduct = productService.getProductById(product_id).getBody();

        var productResponse = productMapper.productToProductResponse(product);

        //Compare new object
        Assertions.assertThat(findNewProduct).isNotNull();
        Assertions.assertThat(findNewProduct).isEqualTo(productResponse);
    }

    @Test
    public void productService_createProduct_returnsProductResponse()
    {
        //Files that send to feign client StorageService for uploading their
        Mockito.when(storageClient.uploadImageFile(Mockito.any(MultipartFile.class)))
                .thenReturn(ResponseEntity.ok(files[1].getOriginalFilename()));

        Mockito.when(userClient.getById(createDto.user_id()))
                .thenReturn(ResponseEntity.ok(Mockito.any()));

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        //Save Product
        var save = productService.createProduct(createDto, files).getBody();

        Assertions.assertThat(save.photos().size()).isEqualTo(2);

        log.info("CreateProduct returns: {}",save);

        //Convert Product to ProductResponse
        var productResponse = productMapper.productToProductResponse(product);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(productResponse).isEqualTo(save);
    }

}
