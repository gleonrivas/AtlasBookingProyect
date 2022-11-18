package com.app.atlasultimate.Utilidades;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ImageConfiguration implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry){
        Path uploadDir = Paths.get("/imgHotel");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/imgHotel/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
