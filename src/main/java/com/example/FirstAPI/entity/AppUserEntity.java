package com.example.FirstAPI.entity;

import com.example.FirstAPI.DTO.UserCreateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class AppUserEntity {

    public AppUserEntity(){

    }

    @Id
    @GeneratedValue
    private Long id;
    private String senha;
    private String nome;
    private Integer idade;
    private double altura;
    private double peso;

    //ID Getters e Stters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    //Senha Getters e Stters
    public String getSenha(){return senha;}
    public void setSenha(String senha){this.senha = senha;}

    //Nome Getters e Stters
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}

    //Idade Getters e Stters
    public Integer getIdade(){return idade;}
    public void setIdade(Integer idade){this.idade = idade;}

    //Altura Getters e Stters
    public double getAltura(){return altura;}
    public void setAltura(double altura){this.altura = altura;}

    //Peso Getters e Stters
    public double getPeso(){return peso;}
    public void setPeso(double peso){this.peso = peso;}
    public AppUserEntity(UserCreateDTO dto){
        this.nome = dto.getNome();
        this.idade = dto.getIdade();
        this.altura = dto.getAltura();
        this.peso = dto.getPeso();
        this.senha = dto.getSenha();
    }




}
