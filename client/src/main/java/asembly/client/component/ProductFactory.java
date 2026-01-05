package asembly.client.component;

import asembly.client.service.CartService;
import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    private final ImageService imageService;
    private final CartService cartService;

    @Autowired
    public ProductFactory(
            ImageService imageService,
            CartService cartService
    ) {
        this.imageService = imageService;
        this.cartService = cartService;
    }

    public Product createProduct(ProductResponse productResponse)
    {
        return new Product(
                productResponse,
                imageService
        );
    }

    public ProductCart createProductCart(ProductResponse productResponse)
    {
        return new ProductCart(
                productResponse,
                imageService,
                cartService
        );
    }

}
