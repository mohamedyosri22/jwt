package com.spring.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;

    private String password;

    private String phone;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


}
