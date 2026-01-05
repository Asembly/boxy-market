package asembly.client.service;

import asembly.client.feign.StorageClient;
import com.vaadin.flow.component.html.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final StorageClient storageClient;

    public Image loadImage(List<String> photos)
    {
        var url = storageClient.getUrl(photos.get(0)).getBody();
        var image = new Image(url, "Image product");

        image.setWidth("150px");
        image.setHeight("150px");

        return image;
    }
}
