package edu.bellevue.cis530.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Main entry point for the API Gateway application.
 * The gateway provides a single entry point for client requests
 * and routes those requests to the appropriate microservice.
 */
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {

        // Start the API Gateway application.
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}