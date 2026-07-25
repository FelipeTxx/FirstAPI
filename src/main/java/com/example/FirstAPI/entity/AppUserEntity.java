package com.example.FirstAPI.entity;

import com.example.FirstAPI.DTO.UserCreateDTO;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class AppUserEntity implements UserDetails {

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
    @OneToMany(mappedBy = "usuario")
    private List<HabitEntity> habito;

    @Column(unique = true, nullable = false)
    private String email;

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

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public List<HabitEntity> getHabito() {return habito;}



    public AppUserEntity(UserCreateDTO dto){
        this.nome = dto.getNome();
        this.idade = dto.getIdade();
        this.altura = dto.getAltura();
        this.peso = dto.getPeso();
        this.senha = dto.getSenha();
        this.email =dto.getEmail();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
