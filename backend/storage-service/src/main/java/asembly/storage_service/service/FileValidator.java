package asembly.user_service.service;

import asembly.exception.storage.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
public class FileValidator {

    public static Boolean isImageFile(MultipartFile file)
    {
        try{
            //Check empty file
            if(file.isEmpty())
                throw new StorageException("Failed to upload empty file.");

            log.info("File type: {}", file.getContentType());

            //Check if the file contains an image type
            var contentType = file.getContentType();
            return List.of("image/jpeg", "image/png").contains(contentType);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
