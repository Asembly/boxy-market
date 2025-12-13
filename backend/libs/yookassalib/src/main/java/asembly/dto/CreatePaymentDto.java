package asembly.dto;

public record CreatePaymentDto(
        AmountDto amount,
        Boolean capture,
        ConfirmationRequestDto confirmation,
        String description
) {
}
