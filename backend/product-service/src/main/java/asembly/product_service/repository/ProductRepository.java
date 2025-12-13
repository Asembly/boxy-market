package asembly.product_service.repository;

import asembly.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "select * from products where user_id = :user_id", nativeQuery = true)
    Optional<List<Product>> findByUserId(String user_id);
}
