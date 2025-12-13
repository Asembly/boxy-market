package asembly.dto;

public record ConfirmationResponseDto(
        String type,
        String confirmation_url
) {
}
