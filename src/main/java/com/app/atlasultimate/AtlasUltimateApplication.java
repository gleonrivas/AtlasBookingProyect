package com.app.atlasultimate;

import com.app.atlasultimate.model.Hotel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class AtlasUltimateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AtlasUltimateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
