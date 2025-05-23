package com.riizestreaming.supportweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //CORS 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins( //허용할 출처
                        "http://www.riizestreaming.com",
                        "https://www.riizestreaming.com",
                        "http://api.riizestreaming.com",
                        "https://api.riizestreaming.com",
                        "http://localhost:3000",
                        "https://localhost:3000"
                )
                .allowedMethods("GET");
    }
}
