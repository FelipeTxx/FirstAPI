package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;

public class UserUpdateDTO {
    private String nome;
    private Integer idade;
    private double altura;
    private double peso;


    public String getNome(){return nome;}
    public Integer getIdade(){return idade;}
    public double getAltura(){return altura;}
    public double getPeso() {return peso;}

}
