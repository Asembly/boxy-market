package asembly.product_service.controller;

import asembly.dto.product.ProductCreateDto;
import asembly.dto.product.ProductResponse;
import asembly.dto.product.ProductUpdateDto;
import asembly.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "product-service")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/get")
    public ResponseEntity<ProductResponse> getProductById(@RequestParam String id){
        return productService.getProductById(id);
    }

    @GetMapping("/get/user")
    public ResponseEntity<List<ProductResponse>> getProductsByUserId(@RequestParam String user_id){
        return productService.getProductsByUserId(user_id);
    }

    @GetMapping("/get/photos")
    public ResponseEntity<List<String>> getProductPhotosUrl(@RequestParam String id)
    {
        return productService.getProductPhotosUrl(id);
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> createProduct(@RequestPart("data") ProductCreateDto dto,@RequestPart("images") MultipartFile[] images){
        return productService.createProduct(dto, images);
    }

    @DeleteMapping("/")
    public ResponseEntity<ProductResponse> deleteProduct(@RequestParam String id){

        return productService.deleteProduct(id);
    }

    @PatchMapping("/")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductUpdateDto dto, @RequestParam String id){

        return productService.updateProduct(dto,id);
    }
}
