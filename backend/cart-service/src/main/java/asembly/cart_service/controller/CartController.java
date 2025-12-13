package asembly.cart_service.controller;

import asembly.cart_service.service.CartService;
import asembly.dto.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart-service")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<List<CartResponse>> getCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/get")
    public ResponseEntity<CartResponse> getCartById(@RequestParam String id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/get/user")
    public ResponseEntity<CartResponse> getCartByUserId(@RequestParam String user_id) {
        return cartService.getCartByUserId(user_id);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addProduct(@RequestParam String id, @RequestParam String product_id) {
        return cartService.addProduct(id, product_id);
    }
}