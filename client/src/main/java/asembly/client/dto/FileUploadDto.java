package asembly.client.dto;

public record FileUploadDto(
        byte[] content,
        String type,
        String filename
        ) {
}
