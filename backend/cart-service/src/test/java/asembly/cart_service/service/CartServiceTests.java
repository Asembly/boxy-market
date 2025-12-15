package asembly.cart_service.service;

import asembly.cart_service.client.ProductClient;
import asembly.cart_service.entity.Cart;
import asembly.cart_service.repository.CartRepository;
import asembly.dto.product.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {


    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductClient productClient;

    private Cart validCart;
    private ProductResponse productResponse;

    @BeforeEach
    public void setup() {
        validCart = Cart.builder()
                .id("test-id-generate")
                .products_id(List.of("", ""))
                .user_id("")
                .build();

        productResponse = ProductResponse.builder()
                .id("test-product-id")
                .user_id("test-user-id")
                .description("test-description")
                .discontinued(false)
                .photos(List.of("test-filename"))
                .price(100)
                .sale(0.0f)
                .created_at(LocalDateTime.now())
                .build();

//        validUser = UserCreateRequest.builder()
//                .username("test")
//                .password("111111111")
//                .build();
//
//        savedUser = User.builder()
//                .id("test-id-generate")
//                .username("test")
//                .password("111111111")
//                .created_at(LocalDateTime.now())
//                .build();
    }

    @Test
    public void addProduct_withValidRequest_returnResponseEntityCartResponse()
    {
        when(productClient.getProductById("test-product-id"))
                .thenReturn(ResponseEntity.ok(productResponse));

        when(cartRepository.save(any(Cart.class)))
                .thenReturn(validCart);

        when(cartRepository.findById("test-id-generate"))
                .thenReturn(Optional.of(validCart));

        var body = productClient.getProductById("test-product-id").getBody();

        assertNotNull(body);

        validCart.getProducts_id().add(body.id());

        var product = cartService.addProduct("test-id-generate", validCart.getId());

        assertNotNull(product);
        assertEquals(200, product.getStatusCode().value());
        assertNotNull(product.getBody());
    }

}
