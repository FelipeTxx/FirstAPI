package com.example.FirstAPI.controller;


import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.service.AppUserService;
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
    public void createUser(@RequestBody AppUserEntity usuario){
        userService.createUser(usuario);
    }

    @GetMapping
    //( @ResponseBody )redundante pois RestController ja faz isso
    public List<AppUserEntity> findUsers(){
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserEntity> findUserById(@PathVariable Long id){
        Optional<AppUserEntity> usuario = userService.findUserById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    public List<AppUserEntity> findByNome(@RequestParam String nome){
        return userService.findByNome(nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@RequestBody AppUserEntity usuario, @PathVariable Long id){
        Optional<AppUserEntity> user = userService.updateUserById(usuario, id);
        if (user.isPresent()){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        Boolean usuarioASerDeletado = userService.deleteUserById(id);

        if(false){
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.noContent().build();



    }



}
