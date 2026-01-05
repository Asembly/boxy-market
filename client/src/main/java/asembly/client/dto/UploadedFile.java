package asembly.client.dto;

public record UploadedFile(
        byte[] content,
        String filename,
        String contentType
) {
}
