package edu.bellevue.cis530.week2.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/*
 * A simple bean that holds API information such as application name and version.
 * This bean is managed by Spring and has a prototype scope, meaning a new instance is created each time it is requested.
 */
@Component
@Scope("prototype")
public class ApiInfoBean {
    
    private String applicationName = "CIS-530 Student Enrollment API";
    private String version = "1.0";

    @PostConstruct
    public void initialize() {
        System.out.println("ApiInfoBean @PostConstruct: Bean initialized.");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("ApiInfoBean @PreDestroy: Bean destroyed.");
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getVersion() {
        return version;
    }

}
