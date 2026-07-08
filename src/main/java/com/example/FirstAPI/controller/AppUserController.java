package com.example.FirstAPI.controller;


import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.service.AppUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<AppUserEntity> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
