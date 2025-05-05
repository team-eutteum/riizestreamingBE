package com.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //허용할 출처
        List<String> origins = List.of("http://api.riizestreaming.com", "https://api.riizestreaming.com", "http://localhost:3000", "https://localhost:3000");

        registry.addMapping("/**")
                .allowedOrigins(String.join(",", origins)) //허용할 출처
                .allowedMethods("GET");
    }
}
