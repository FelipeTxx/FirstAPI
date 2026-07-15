package com.example.FirstAPI.controller;


import com.example.FirstAPI.DTO.UserCreateDTO;
import com.example.FirstAPI.DTO.UserResponseDTO;
import com.example.FirstAPI.DTO.UserUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.service.AppUserService;
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
    public ResponseEntity<AppUserEntity> createUser(@RequestBody UserCreateDTO usuario){
        Optional<AppUserEntity> user = userService.createUser(usuario);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @GetMapping

    public List<UserResponseDTO> findUsers(){
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id){
        Optional<UserResponseDTO> usuario = userService.findUserById(id);
        if(usuario.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(usuario.get());
    }
    @GetMapping("/search")
    public List<UserResponseDTO> findByNome(@RequestParam String nome){
        return userService.findByNome(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@RequestBody UserUpdateDTO usuario, @PathVariable Long id){
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
