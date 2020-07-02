package pl.umk.mat.server.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private String imageRegex = "jpeg|jpg|png";
    private String imageDir = "/";

    public String getImageRegex() {
        return imageRegex;
    }

    public String getImageDir() {
        return imageDir;
    }
}
