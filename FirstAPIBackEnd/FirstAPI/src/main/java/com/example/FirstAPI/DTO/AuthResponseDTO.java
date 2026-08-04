package com.example.FirstAPI.DTO;

public class AuthResponseDTO {
    private String token;

    public String getToken() {return token;}

    public AuthResponseDTO(String token){
        this.token = token;
    }
}
