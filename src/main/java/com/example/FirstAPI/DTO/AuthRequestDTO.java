package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public String getEmail() {return email;}
    public String getSenha() {return senha;}

    public AuthRequestDTO(){

    }

}
