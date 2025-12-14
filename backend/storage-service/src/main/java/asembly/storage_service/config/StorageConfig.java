package asembly.user_service.config;

import asembly.exception.storage.StorageException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
@Getter
public class StorageConfig {

    private Path rootDir;
    private String baseUrl;

    @Autowired
    public StorageConfig(
            @Value("${static.folder.path}") String location,
            @Value("${server.hostname}") String hostname,
            @Value("${server.port}") String port
    )
    {
        try
        {
            baseUrl = "http://" + hostname + ":" + port + "/files/";
            log.info("Url static path: {}", baseUrl);
            log.info("Location: {}",location);
            rootDir = Paths.get(location).toAbsolutePath().normalize();

            log.info(Paths.get(rootDir + "/images/").toString());

            //Initialization folders
            Files.createDirectories(rootDir);
        }
        catch (IOException e)
        {
            throw new StorageException("Failed to create directories", e.getCause());
        }

    }

}
