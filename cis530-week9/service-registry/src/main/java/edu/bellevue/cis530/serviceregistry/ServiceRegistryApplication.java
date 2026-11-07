package edu.bellevue.cis530.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
 * Main entry point for the Eureka Service Registry.
 * This application acts as the central registry where
 * microservices register themselves and discover other services.
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    public static void main(String[] args) {

        // Start the Eureka Service Registry application.
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}