package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.UserCreateDTO;
import com.example.FirstAPI.DTO.UserResponseDTO;
import com.example.FirstAPI.DTO.UserUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository repository;



    public AppUserService(AppUserRepository repository ) {
        this.repository = repository;

    }

    public Optional<AppUserEntity> createUser(UserCreateDTO dto) {
        Optional<AppUserEntity> entity = Optional.of(new AppUserEntity(dto));

        AppUserEntity userCreated =  repository.save(entity.get());
        return Optional.of(userCreated);
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

        AppUserEntity user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario Não encontrado!"));

        UserResponseDTO dto = new UserResponseDTO(user);

        return dto;

    }

    public List<UserResponseDTO> findByNome(String nome){
        List<AppUserEntity> user = repository.findByNome(nome);
        List<UserResponseDTO> dtoUsers = new ArrayList<>();
        for (AppUserEntity i: user){//isso daqui ja me da direto o usuario buscado, nem preciso do get
            UserResponseDTO dto = new UserResponseDTO(i);
            dtoUsers.add(dto);
        }
        return dtoUsers;
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
}
