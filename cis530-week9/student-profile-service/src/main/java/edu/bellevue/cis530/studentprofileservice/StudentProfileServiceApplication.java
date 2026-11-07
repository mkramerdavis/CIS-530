package edu.bellevue.cis530.studentprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Main entry point for the Student Profile Service microservice.
 * Spring Boot handles application configuration and component scanning.
 */
@SpringBootApplication
public class StudentProfileServiceApplication {

    public static void main(String[] args) {

        // Start the Student Profile Service application.
        SpringApplication.run(StudentProfileServiceApplication.class, args);
    }
}