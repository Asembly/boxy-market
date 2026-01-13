package asembly.client.service;

import asembly.client.feign.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private StorageClient storageClient;


    public String getUrl(String photo)
    {
        return storageClient.getUrl(photo).getBody();
    }

    public List<String> getUrls(List<String> photos)
    {
        List<String> urls = new ArrayList<>();
        photos.forEach(item -> {
            urls.add(storageClient.getUrl(item).getBody());
        });

        return urls;
    }

}
