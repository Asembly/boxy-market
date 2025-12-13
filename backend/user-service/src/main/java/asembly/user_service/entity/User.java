package asembly.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(min = 6)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate created_at;
}
