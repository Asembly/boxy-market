package asembly.dto.cart;

import java.util.List;

public record CartResponse(
        String id,
        String user_id,
        List<String> products_id
) {
}
