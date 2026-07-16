package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import jakarta.validation.constraints.*;

public class UserUpdateDTO {
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



    public String getNome(){return nome;}
    public Integer getIdade(){return idade;}
    public double getAltura(){return altura;}
    public double getPeso() {return peso;}

    public UserUpdateDTO(){

    }
}
