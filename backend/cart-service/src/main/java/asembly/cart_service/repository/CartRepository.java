package asembly.cart_service.repository;

import asembly.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    @Query(value = "select * from carts where :user_id = user_id", nativeQuery = true)
    Optional<Cart> findCartByUserId(String user_id);

    @Query(value = "select * from carts where :products_id = product_id", nativeQuery = true)
    Optional<Cart> findCartByProductId(String user_id);
}
