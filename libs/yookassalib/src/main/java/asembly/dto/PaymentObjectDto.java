package asembly.dto;

public record PaymentObjectDto(
        String id,
        String status,
        AmountDto amount
) {
}
