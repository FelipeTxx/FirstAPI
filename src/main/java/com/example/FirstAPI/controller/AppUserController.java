package com.example.FirstAPI.controller;


import com.example.FirstAPI.DTO.UserCreateDTO;
import com.example.FirstAPI.DTO.UserResponseDTO;
import com.example.FirstAPI.DTO.UserUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.service.AppUserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreateDTO usuario){
        UserResponseDTO user = userService.createUser(usuario);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{me}")
    public ResponseEntity<@Valid UserResponseDTO> findUserById(){
        UserResponseDTO usuario = userService.findUserById();
        return ResponseEntity.ok(usuario);
    }



    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUserById(@Valid @RequestBody UserUpdateDTO usuario){
        Optional<UserResponseDTO> user = userService.updateUserById(usuario);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/delete/me")
    public ResponseEntity<Void> deleteUserById(){
        Boolean usuarioASerDeletado = userService.deleteUserById();

        if(usuarioASerDeletado){
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.noContent().build();



    }



}
