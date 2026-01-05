package asembly.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "storage-client", url = "${feign.services}/storage-service/")
public interface StorageClient {

    @GetMapping("/get/url")
    public ResponseEntity<String> getUrl(@RequestParam("filename") String filename);

}