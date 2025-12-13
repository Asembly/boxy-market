package asembly.payment_service.entity;

import asembly.dto.AmountDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@RedisHash
public class Payment {
    @Id
    @Indexed
    String id;
    String status;
    AmountDto amount;
    String description;
    LocalDateTime created_at;
}
