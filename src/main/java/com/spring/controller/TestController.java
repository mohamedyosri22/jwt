package com.spring.controller;

import com.spring.config.CustomAuthProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/yes")
    public String yes(){
        return "iam yes";
    }

    @GetMapping("/no")
    public String no(){
        return "hi iam no";
    }
}
