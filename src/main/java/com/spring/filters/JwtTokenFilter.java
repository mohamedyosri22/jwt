package com.spring.filters;

import com.spring.config.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/token");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();



        if(authentication!=null){
            SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setSubject("JWT TOKEN")
                    .claim("email",authentication.getName())
                    .claim("authorities",getAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+150000))
                    .signWith(secretKey).compact();

            response.setHeader(SecurityConstants.HEADER,jwt);
        }
        filterChain.doFilter(request,response);

    }

    private String getAuthorities(Collection<? extends GrantedAuthority> collations){
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority : collations){
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths); //"User,ADMIN"
    }
}
