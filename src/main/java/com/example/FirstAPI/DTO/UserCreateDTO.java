package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class UserCreateDTO {
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
    @NotBlank
    @Size(min = 6, max = 100)
    private String senha;
    @Column(unique = true, nullable = false)
    private String email;

    public String getNome(){return nome;}
    public Integer getIdade(){return idade;}
    public double getAltura(){return altura;}
    public double getPeso() {return peso;}
    public String getSenha() {return senha;}
    public String getEmail() {return email;}

    public UserCreateDTO(){

    }

}
