package asembly.user_service.service;

import asembly.exception.storage.StorageException;
import asembly.exception.storage.StorageFileNotFoundException;
import asembly.exception.storage.StorageNotValidType;
import asembly.user_service.config.StorageConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;

@Slf4j
@Service
public class StorageService {

    @Autowired
    private StorageConfig config;

    public ResponseEntity<String> uploadImageFile(MultipartFile file)
    {
        try{
            if (!FileValidator.isImageFile(file))
                throw new StorageNotValidType("The file type not match image type.");
            var filename = UUID.randomUUID() + file.getOriginalFilename();

            log.info("Original filename: {}", file.getOriginalFilename());

            Path destinationFile = this.config.getRootDir().resolve(Paths.get(filename)).normalize()
                    .toAbsolutePath();

            log.info("Destination file: {}", destinationFile);

            if (!destinationFile.getParent().equals(this.config.getRootDir().toAbsolutePath()))
                throw new StorageException("Cannot store file outside current directory.");

            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok(filename);
        }catch(IOException e)
        {
            throw new StorageException("Failed to upload file.", e);
        }
    }

    public void deleteFile(String filename)
    {
        try
        {
            Path destinationFile = this.config.getRootDir().resolve(Paths.get(filename)).normalize().toAbsolutePath();
            log.info("Destination file path: {}",destinationFile.toString());
            Files.deleteIfExists(destinationFile);
        }
        catch (NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch (DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch (IOException e)
        {
            System.out.println("Invalid permissions.");
        }

    }

    public ResponseEntity<File> getFile(String filename){
        try{
            Path destinationFile = this.config.getRootDir().resolve(Paths.get(filename)).normalize().toAbsolutePath();
            File file = new File(destinationFile.toUri());

            if(file.exists() && file.canRead())
                return ResponseEntity.ok(file);
            else
                throw new StorageFileNotFoundException("Could not read the file: " + filename);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> getUrl(String filename){
        try{
            log.info("LoadResource filename: {}", filename);
            Path destinationFile = this.config.getRootDir().resolve(Paths.get(filename)).normalize().toAbsolutePath();
            Resource file = new UrlResource(destinationFile.toUri());

            if(file.exists() && file.isReadable())
                return ResponseEntity.ok(config.getBaseUrl() + filename);
            else
                throw new StorageFileNotFoundException("Could not read the file: " + filename);

        } catch (RuntimeException | MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Resource> getResource(String filename){
        try{
            log.info("LoadResource filename: {}", filename);
            Path destinationFile = this.config.getRootDir().resolve(Paths.get(filename)).normalize().toAbsolutePath();
            Resource file = new UrlResource(destinationFile.toUri());

            if(file.exists() && file.isReadable())
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file);
            else
                throw new StorageFileNotFoundException("Could not read the file: " + filename);

        } catch (RuntimeException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}