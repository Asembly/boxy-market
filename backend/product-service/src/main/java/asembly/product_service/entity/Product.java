package asembly.product_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Product {
    @Id
    @NotBlank
    private String id;
    @NotBlank
    private String user_id;
    private Integer price;
    private Float sale;
    @NotBlank
    @Size(max=100)
    private String title;
    @NotBlank
    @Size(max=1000)
    private String description;
    private Boolean discontinued;
    @NotNull
    private List<String> photos;
    private LocalDateTime created_at;
}
