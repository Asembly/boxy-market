package asembly.cart_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Cart {

    @Id
    private String id;
    @NotBlank
    @Column(unique = true)
    private String user_id;
    private List<String> products_id;
}
