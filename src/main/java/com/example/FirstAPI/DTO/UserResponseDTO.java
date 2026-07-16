package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.repository.AppUserRepository;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Optional;

public class UserResponseDTO {


    @NotBlank
    private String nome;
    @NotNull
    @Min(0)
    @Max(200)
    private Integer idade;
    @DecimalMin("0.10")
    @DecimalMax("3.00")
    @NotNull
    private double altura;
    @NotNull
    @DecimalMin("5")
    @DecimalMax("500")
    private double peso;




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

        this.nome = usuario.getNome();
        this.altura = usuario.getAltura();
        this.peso = usuario.getPeso();

    }



}
