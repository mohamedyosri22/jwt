package com.spring.controller;

import com.spring.dto.UserRegistrationDto;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/all-data")
    public List<User> getAllData(){
        return userService.getAllUsers();
    }

    @PostMapping("/new-admin")
    public ResponseEntity<?> saveAdmin(@RequestBody UserRegistrationDto adminDto){
        User admin = userService.saveAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(admin);
    }
}
