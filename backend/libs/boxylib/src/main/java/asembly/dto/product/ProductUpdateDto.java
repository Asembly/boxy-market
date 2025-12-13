package asembly.dto.product;

import lombok.Builder;

@Builder
public record ProductUpdateDto(
        Integer price,
        Float sale,
        String title,
        String description,
        Boolean discontinued
) {}
