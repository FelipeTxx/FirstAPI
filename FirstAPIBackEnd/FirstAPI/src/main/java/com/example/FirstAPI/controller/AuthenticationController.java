package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.AuthRequestDTO;
import com.example.FirstAPI.DTO.AuthResponseDTO;
import com.example.FirstAPI.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<@Valid AuthResponseDTO> authenticateUser(@RequestBody AuthRequestDTO login){
        return ResponseEntity.ok(service.authenticateUser(login));
    }
}
