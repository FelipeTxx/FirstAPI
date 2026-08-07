package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.UserCreateDTO;
import com.example.FirstAPI.DTO.UserResponseDTO;
import com.example.FirstAPI.DTO.UserUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
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
    private final CurrentUserService currentUserService;


    public AppUserService(AppUserRepository repository, PasswordEncoder passwordEncoder, CurrentUserService currentUserService) {
        this.repository = repository;

        this.passwordEncoder = passwordEncoder;
        this.currentUserService = currentUserService;
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


    public UserResponseDTO findUserById() {
        Long id = currentUserService.getAuthenticatedUser().getId();
        return repository.findById(id).map(UserResponseDTO::new).orElseThrow(UserNotFoundException::new);
    }

    public Optional<UserResponseDTO> updateUserById(UserUpdateDTO usuario) {
        Long id = currentUserService.getAuthenticatedUser().getId();
        Optional<AppUserEntity> usuarioEncontrado = repository.findById(id);
        if(usuarioEncontrado.isEmpty()){return Optional.empty();}

        AppUserEntity usuarioAtualizado = usuarioEncontrado.get();
        if(usuario.getNome()!=null){usuarioAtualizado.setNome(usuario.getNome());}
        if(usuario.getIdade()!=0){usuarioAtualizado.setIdade(usuario.getIdade());}
        if(usuario.getAltura()!=0.0){usuarioAtualizado.setAltura(usuario.getAltura());}
        if(usuario.getPeso()!=0.0){usuarioAtualizado.setPeso(usuario.getPeso());}
        repository.save(usuarioAtualizado);

        UserResponseDTO dto = new UserResponseDTO(usuarioAtualizado);

        return Optional.of(dto);


    }

    public Boolean deleteUserById() {
        Long id = currentUserService.getAuthenticatedUser().getId();
        Optional<AppUserEntity> usuarioASerDeletado = repository.findById(id);
        if(usuarioASerDeletado.isEmpty()){ return false; }
        repository.deleteById(id);
        return true;
    }


}
