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

    @GetMapping

    public List<UserResponseDTO> findUsers(){
        return userService.findUsers();
    }

    @GetMapping("/{id}")

    public ResponseEntity<@Valid UserResponseDTO> findUserById(@PathVariable Long id){
        UserResponseDTO usuario = userService.findUserById(id);

        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/search")

    public List<@Valid UserResponseDTO> findByNome(@RequestParam String nome){
        return userService.findByNome(nome);
    }

    @GetMapping("/searchEmail")
    public ResponseEntity<@Valid UserResponseDTO> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@Valid @RequestBody UserUpdateDTO usuario, @PathVariable Long id){
        Optional<UserResponseDTO> user = userService.updateUserById(usuario, id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        Boolean usuarioASerDeletado = userService.deleteUserById(id);

        if(usuarioASerDeletado){
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.noContent().build();



    }



}
