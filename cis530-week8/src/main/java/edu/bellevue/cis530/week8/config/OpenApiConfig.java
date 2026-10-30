package edu.bellevue.cis530.week8.config;

/*
 * Bellevue University. (n.d.). Assignment 8.3 – API Documentation with Swagger / OpenAPI for the Student Services API. Course Handout.
 */

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// Configuration class for customizing Swagger / OpenAPI documentation
@Configuration
public class OpenApiConfig {

    // Defines the general information displayed in Swagger UI
    @Bean
    public OpenAPI studentServicesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Services API - Bellevue University")
                        .description("OpenAPI documentation for the CIS 530 Student Services API")
                        .version("v1.0.0")
                        .contact(new Contact().name("CIs 530").email("student@bellevue.edu"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Springdoc reference")
                        .url("https://springdoc.org/"));
    }
}
