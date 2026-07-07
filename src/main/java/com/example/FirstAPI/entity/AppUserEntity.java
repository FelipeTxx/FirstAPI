package com.example.FirstAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class AppUserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String senha;
    private String nome;
    private Integer idade;
    private double altura;
    private double peso;


}
