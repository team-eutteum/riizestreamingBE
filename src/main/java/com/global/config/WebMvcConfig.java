package com.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins( //허용할 출처
                        "http://api.riizestreaming.com",
                        "https://api.riizestreaming.com",
                        "http://localhost:3000",
                        "https://localhost:3000"
                ) //허용할 출처
                .allowedMethods("GET");
    }
}
