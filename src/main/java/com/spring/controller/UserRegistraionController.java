package com.spring.controller;

import com.spring.dto.UserRegistrationDto;
import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRegistraionController {
    private UserService userService;

    @Autowired
    public UserRegistraionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDto userDto){
        User user = userService.saveUser(userDto);
        if(user == null){
            throw new RuntimeException("something went wrong");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(user);
    }

    @GetMapping("/register")
    public String test(){
        return "test done";
    }

}
