package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.AuthRequestDTO;
import com.example.FirstAPI.DTO.AuthResponseDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.exception.PasswordNotMatchesException;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import com.example.FirstAPI.security.CustomUserDetailsService;
import com.example.FirstAPI.security.JwtService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    final AppUserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final JwtService jwtService;


    public AuthenticationService(AppUserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO authenticateUser(AuthRequestDTO login) {
        UsernamePasswordAuthenticationToken authent = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        Object userAuthenticated = authenticationManager.authenticate(authent).getPrincipal();
        return new AuthResponseDTO(jwtService.generateToken((UserDetails) userAuthenticated));

    }
}
