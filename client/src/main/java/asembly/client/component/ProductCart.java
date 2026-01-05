package asembly.client.component;

import asembly.client.service.CartService;
import asembly.client.service.ImageService;
import asembly.dto.product.ProductResponse;

public class ProductCart extends Product {

    private final CartService cartService;

    public ProductCart(
            ProductResponse product,
            ImageService imageService,
            CartService cartService
    )
    {
        super(product, imageService);
        this.cartService = cartService;
        productContainer.add(cartService.addToCartButton(product.id()));
    }

    @Override
    protected void init()
    {
        productContainer.add(titleField);
        productContainer.add(descriptionField);
        productContainer.add(
                imageService.loadImage(product.photos())
        );
        productContainer.add(priceField);
        add(productContainer);
    }
}
