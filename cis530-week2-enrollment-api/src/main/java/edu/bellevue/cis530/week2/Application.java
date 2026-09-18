package edu.bellevue.cis530.week2;

import edu.bellevue.cis530.week2.config.ApiInfoBean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/*
 * Main entry point for the Spring Boot application.
 */
@SpringBootApplication
public class Application {
    
    // Main method to start the Spring Boot application. //
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @org.springframework.context.annotation.Bean
    CommandLineRunner demonstratePrototypeScope(ApplicationContext context) {
        return args -> {

            ApiInfoBean bean1 = context.getBean(ApiInfoBean.class);
            ApiInfoBean bean2 = context.getBean(ApiInfoBean.class);

            System.out.println("ApiInfoBean prototype test:");
            System.out.println("Bean 1: " + bean1);
            System.out.println("Bean 2: " + bean2);
            System.out.println("Are Bean 1 and Bean 2 the same object? "
                    + (bean1 == bean2));
        };

    }

}
