package asembly.dto.product;

import lombok.Builder;

@Builder
public record ProductCreateDto(
        String user_id,
        String title,
        String description,
        Integer price
) {}
