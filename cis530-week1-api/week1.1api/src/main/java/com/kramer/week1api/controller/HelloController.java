package com.kramer.week1api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Welcome to CIS 530 - Week 1";
    }

    @GetMapping ("/info")
    public Map<String, Object> info() {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("course", "CIS-530: Server-Side Development");
        data.put("week", 1);
        data.put("technology", "Spring Boot");
        data.put("instructor", "Professor Richard Krasso");
        data.put("semester", "Fall 2026");
        data.put("institution", "Bellevue University");
        
        return data;
    }
}
