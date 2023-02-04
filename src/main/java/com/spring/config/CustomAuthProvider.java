package com.spring.config;

import com.spring.model.Role;
import com.spring.model.User;
import com.spring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;

    @Autowired
    public CustomAuthProvider(PasswordEncoder passwordEncoder,UserRepo userRepo) {
        this.passwordEncoder=passwordEncoder;
        this.userRepo=userRepo;
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepo.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("User name not found");

        }else{
            if(passwordEncoder.matches(password,user.getPassword())){
                return new UsernamePasswordAuthenticationToken(email,password,getRoles(user.getRoles()));
            }
            else{
                throw new BadCredentialsException("wrong password");
            }
        }
    }

    private List<SimpleGrantedAuthority> getRoles(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority(role.getName()));
        }
        return list;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
