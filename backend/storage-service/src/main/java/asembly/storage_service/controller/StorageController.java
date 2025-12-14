package asembly.user_service.controller;

import asembly.user_service.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RestController
@RequestMapping(path = "storage-service")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImageFile(@RequestPart("image") MultipartFile image)
    {
        return storageService.uploadImageFile(image);
    }

    @GetMapping("/get/url")
    public ResponseEntity<String> getUrl(@RequestParam("filename") String filename){
        return storageService.getUrl(filename);
    }

    @GetMapping("/get/resource")
    public ResponseEntity<Resource> getResource(@RequestParam("filename") String filename)
    {
        return storageService.getResource(filename);
    }

    @GetMapping("/get/file")
    public ResponseEntity<File> getFile(@RequestParam("filename") String filename){
        return storageService.getFile(filename);
    }

}
