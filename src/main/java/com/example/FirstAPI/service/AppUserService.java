package com.example.FirstAPI.service;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.repository.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

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


    public Optional<AppUserEntity> findUserById(Long id) {

        return repository.findById(id);
    }

    public List<AppUserEntity> findByNome(String nome){
        return repository.findByNome(nome);
    }


    public Optional<AppUserEntity> updateUserById(AppUserEntity usuario, Long id) {
        Optional<AppUserEntity> usuarioEncontrado = repository.findById(id);
        if(usuarioEncontrado.isEmpty()){return Optional.empty();}

        AppUserEntity usuarioAtualizado = usuarioEncontrado.get();

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setSenha(usuario.getSenha());
        usuarioAtualizado.setIdade(usuario.getIdade());
        usuarioAtualizado.setAltura(usuario.getAltura());
        usuarioAtualizado.setPeso(usuario.getPeso());
        repository.save(usuarioAtualizado);
        return Optional.of(usuarioAtualizado);


    }

    public Boolean deleteUserById(Long id) {
        Optional<AppUserEntity> usuarioASerDeletado = repository.findById(id);
        if(usuarioASerDeletado.isEmpty()){ return false; }
        repository.deleteById(id);
        return true;
    }
}
