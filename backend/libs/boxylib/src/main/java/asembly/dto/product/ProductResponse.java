package asembly.dto.product;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductResponse(
        String id,
        String user_id,
        Integer price,
        Float sale,
        String title,
        String description,
        List<String> photos,
        LocalDateTime created_at
) {
}
