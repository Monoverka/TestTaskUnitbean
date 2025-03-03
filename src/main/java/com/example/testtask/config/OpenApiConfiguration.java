package com.example.testtask.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {
    @Bean
    GroupedOpenApi openApi() {

                return GroupedOpenApi.builder()
                        .group("public")
                        .pathsToMatch("/**")
                        .build();


    }
}
