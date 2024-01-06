package com.steffi.dorfladen.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/names")
public class TestController {
    
    @GetMapping
    public List<String> getNames() {
        return Arrays.asList("Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Isabella", "Lucas");
    }
}