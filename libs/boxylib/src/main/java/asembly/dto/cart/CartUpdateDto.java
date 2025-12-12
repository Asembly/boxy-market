package asembly.dto.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartUpdateDto(
        List<String> products_id
) {}
