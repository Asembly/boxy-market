package asembly.dto;

public record ConfirmationRequestDto(
        String type,
        String return_url
) {
}
