package com.asif.campusvoting.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI campusVotingOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Campus Voting System API")
                        .version("1.0")
                        .description("Secure REST API for Campus Voting System built using Spring Boot, Spring Security, JWT and MySQL.")
                        .contact(new Contact()
                                .name("Asif Sameer")
                                .email("asifsameer536@gmail.com")
                                .url("https://github.com/AsifSameer536")));
    }
}