package com.riizestreaming.supportweb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("RiizeStreaming API Document")
                        .version("v0.0.1")
                        .description("RiizeStreaming 프로젝트의 API 명세서입니다.")
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server URL"),
                        new Server().url("https://api.riizestreaming.com").description("Prod Server URL")
                ));
    }
}
