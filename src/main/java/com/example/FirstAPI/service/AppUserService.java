package com.example.FirstAPI.service;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository repository;


    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public void createUser(AppUserEntity usuario) {
        repository.save(usuario);
    }
    public List<AppUserEntity> findUsers(){
        return repository.findAll();
    }


    public Optional<AppUserEntity> getUserById(Long id) {
        return repository.findById(id);
    }


}
