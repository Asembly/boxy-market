package asembly.dto;

public record PaymentEventDto(
        String type,
        String event,
        PaymentObjectDto object
) {
}
