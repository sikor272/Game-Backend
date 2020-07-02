package pl.umk.mat.server.resources;

import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.umk.mat.server.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/resources")
@Api(description = "This controller provides to get resources.", tags = "Resource Controller")
public class ResourcesController {
    private Config config;

    public ResourcesController(Config config) {
        this.config = config;
    }

    @RequestMapping("/{imageName}")
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(
            @PathVariable String imageName
    ) throws IOException {
        byte[] image = Files.readAllBytes(Paths.get(config.getImageDir() + imageName));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
}
