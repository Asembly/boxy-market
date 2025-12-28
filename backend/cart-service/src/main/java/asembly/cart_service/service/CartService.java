package asembly.cart_service.service;

import asembly.cart_service.client.ProductClient;
import asembly.cart_service.entity.Cart;
import asembly.cart_service.mapper.CartMapper;
import asembly.cart_service.repository.CartRepository;
import asembly.dto.cart.CartResponse;
import asembly.exception.cart.CartNotFoundException;
import asembly.utils.GeneratorId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductClient productClient;

    private final CartMapper cartMapper = CartMapper.INSTANCE;

    public ResponseEntity<CartResponse> getCartById(String id){
        var cart = cartRepository.findById(id).orElseThrow(
                CartNotFoundException::new
        );

        var cartResponse = cartMapper.cartToCartResponse(cart);

        return ResponseEntity.ok(cartResponse);
    }

    public ResponseEntity<List<CartResponse>> getCarts(){
        var carts = cartRepository.findAll();

        log.info("FindAll Carts:{}", carts);
        var cartsResponse = carts.stream().map(cartMapper::cartToCartResponse).toList();

        return ResponseEntity.ok(cartsResponse);
    }

    public ResponseEntity<CartResponse> getCartByUserId(String user_id){
        var cart = cartRepository.findCartByUserId(user_id).orElseThrow(
                CartNotFoundException::new
        );

        var cartResponse = cartMapper.cartToCartResponse(cart);

        return ResponseEntity.ok(cartResponse);
    }


    public ResponseEntity<CartResponse> addProduct(String user_id, String product_id){

        //Find product by id, check empty or not
        var product = productClient.getProductById(product_id).getBody();

        var cart = cartRepository.findCartByUserId(user_id).orElseThrow(
                        CartNotFoundException::new
                );

        cart.getProducts_id().add(product_id);

        var save = cartRepository.save(cart);

        var cartResponse = cartMapper.cartToCartResponse(save);

        return ResponseEntity.ok(cartResponse);
    }

    public Cart createCart(String user_id) {

        var cart = new Cart(
                GeneratorId.generateId(),
                user_id,
                List.of()
        );

        return cartRepository.save(cart);
    }
}
