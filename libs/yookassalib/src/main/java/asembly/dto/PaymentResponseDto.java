package asembly.dto;

import java.time.LocalDateTime;

public record PaymentResponseDto(
        String id,
        String status,
        Boolean paid,
        AmountDto amount,
        ConfirmationResponseDto confirmation,
        LocalDateTime created_at,
        String description
) {
}
