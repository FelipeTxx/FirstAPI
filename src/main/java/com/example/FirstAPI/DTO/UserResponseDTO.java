package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.repository.AppUserRepository;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Optional;

public class UserResponseDTO {


    private Long id;
    private  String nome;
    private double altura;
    private  double peso;

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public double getAltura(){
        return altura;
    }

    public double getPeso(){
        return peso;
    }




    public UserResponseDTO(AppUserEntity usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.altura = usuario.getAltura();
        this.peso = usuario.getPeso();

    }



}
