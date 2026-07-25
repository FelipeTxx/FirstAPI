package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.UserCreateDTO;
import com.example.FirstAPI.DTO.UserResponseDTO;
import com.example.FirstAPI.DTO.UserUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AppUserService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;



    public AppUserService(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;

        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO createUser(UserCreateDTO dto) {
        AppUserEntity entity = new AppUserEntity(dto);
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));

        repository.save(entity);
        return new UserResponseDTO(entity);
    }
    public List<UserResponseDTO> findUsers(){
        List<AppUserEntity> user = repository.findAll();
        List<UserResponseDTO> dtoUsers = new ArrayList<>();
        for (AppUserEntity i : user) {
            UserResponseDTO dto = new UserResponseDTO(i);
            dtoUsers.add(dto);
        }
        return dtoUsers;
    }


    public UserResponseDTO findUserById(Long id) {
         return repository.findById(id).map(UserResponseDTO::new).orElseThrow(UserNotFoundException::new);
    }

    public List<UserResponseDTO> findByNome(String nome){
        return repository.findByNome(nome).stream().map(UserResponseDTO::new).toList();
    }


    public Optional<UserResponseDTO> updateUserById(UserUpdateDTO usuario, Long id) {

        Optional<AppUserEntity> usuarioEncontrado = repository.findById(id);
        if(usuarioEncontrado.isEmpty()){return Optional.empty();}

        AppUserEntity usuarioAtualizado = usuarioEncontrado.get();

        usuarioAtualizado.setNome(usuario.getNome());
        usuarioAtualizado.setIdade(usuario.getIdade());
        usuarioAtualizado.setAltura(usuario.getAltura());
        usuarioAtualizado.setPeso(usuario.getPeso());
        repository.save(usuarioAtualizado);

        UserResponseDTO dto = new UserResponseDTO(usuarioAtualizado);

        return Optional.of(dto);


    }

    public Boolean deleteUserById(Long id) {
        Optional<AppUserEntity> usuarioASerDeletado = repository.findById(id);
        if(usuarioASerDeletado.isEmpty()){ return false; }
        repository.deleteById(id);
        return true;
    }

    public UserResponseDTO findByEmail(String email) {
         return (repository.findByEmail(email).map(UserResponseDTO::new).orElseThrow());
    }
}
