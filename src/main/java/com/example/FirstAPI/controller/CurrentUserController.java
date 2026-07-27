package com.example.FirstAPI.controller;


import com.example.FirstAPI.DTO.UserAuthenticatedResponseDTO;
import com.example.FirstAPI.repository.AppUserRepository;
import com.example.FirstAPI.service.AppUserService;
import com.example.FirstAPI.service.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CurrentUserController {


    final CurrentUserService currentUserService;

    public CurrentUserController(CurrentUserService currentUserService) {

        this.currentUserService = currentUserService;
    }

    @PostMapping("/me")
    public ResponseEntity<UserAuthenticatedResponseDTO> me(){
        return ResponseEntity.ok(currentUserService.getAuthenticatedUser());
    }

}
