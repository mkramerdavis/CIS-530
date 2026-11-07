package edu.bellevue.cis530.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * Main entry point for the Student Service microservice.
 * Enables component scanning, Spring Boot auto-configuration,
 * and OpenFeign so this service can communicate with other services.
 */
@SpringBootApplication
@EnableFeignClients
public class StudentServiceApplication {

    public static void main(String[] args) {

        // Start the Student Service application.
        SpringApplication.run(StudentServiceApplication.class, args);
    }
}