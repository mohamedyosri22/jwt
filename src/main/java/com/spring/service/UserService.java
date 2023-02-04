package com.spring.service;

import com.spring.dto.UserRegistrationDto;
import com.spring.model.Role;
import com.spring.model.User;
import com.spring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserRegistrationDto userDto){
        User user = new User(userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),userDto.getPhone(), Arrays.asList(new Role("ROLE_USER")));

        return userRepo.save(user);
    }

    public User saveAdmin(UserRegistrationDto adminDto){
        User admin = new User(adminDto.getEmail(),passwordEncoder.encode(adminDto.getPassword()),adminDto.getPhone(), Arrays.asList(new Role("ROLE_ADMIN")));

        return userRepo.save(admin);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
