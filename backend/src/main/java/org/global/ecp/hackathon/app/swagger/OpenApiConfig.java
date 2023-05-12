package org.global.ecp.hackathon.app.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().info(new Info()
                                          .title("E-Commerce App Backend Endpoints")
                                          .description("REST API Endpoints for E-Commerce App")
                                          .version("v1"));
    }
}