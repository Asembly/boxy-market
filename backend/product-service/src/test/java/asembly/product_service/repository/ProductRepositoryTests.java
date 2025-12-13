package asembly.product_service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

//    @Test
//    public void ProductRepository_FindAll_ReturnMoreThenOneProduct()
//    {
//        //Arrange
//        List<Product> products = List.of(
//                Product.builder()
//                        .id("1db3ses1")
//                        .user_id("142dfg12")
//                        .title("Product 1")
//                        .description("Product 1 for test")
//                        .build(),
//                Product.builder()
//                        .id("25seg10a")
//                        .user_id("142dfg12")
//                        .title("Product 2")
//                        .description("Product 2 for test")
//                        .build()
//                );
//
//        productRepository.saveAll(products);
//
//        //Act
//
//        List<Product> allProducts = productRepository.findAll();
//
//        //Assert
//
//        Assertions.assertNotNull(allProducts);
//        Assertions.assertTrue(allProducts.contains(products.get(0)));
//        Assertions.assertTrue(allProducts.contains(products.get(1)));
//        Assertions.assertEquals(2, allProducts.size());
//    }
//
//    @Test
//    public void ProductRepository_Save_ReturnOneProduct()
//    {
//        Product product = Product.builder()
//                .id("25seg10a")
//                .user_id("142dfg12")
//                .title("Product 2")
//                .description("Product 2 for test")
//                .build();
//
//        productRepository.save(product);
//
//        List<Product> savedProduct = productRepository.findAll();
//
//        Assertions.assertNotNull(savedProduct);
//        Assertions.assertEquals(1, savedProduct.size());
//    }
}
