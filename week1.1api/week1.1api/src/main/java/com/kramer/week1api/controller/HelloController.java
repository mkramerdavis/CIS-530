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
        return "Kramer, Welcome to CIS-530 Course!";
    }

    @GetMapping ("/info")
    public Map<String, Object> info() {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("name", "Marc Kramer-Davis");
        data.put("institution", "Bellevue University");
        data.put("course", "CIS-530: Server-Side Development");
        data.put("instructor", "Professor Richard Krasso");
        data.put("week", 1);
        data.put("message", "Welcome to the course!");
        
        return data;
    }
}
