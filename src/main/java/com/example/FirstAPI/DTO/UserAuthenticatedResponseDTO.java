package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import jakarta.validation.constraints.NotNull;

public class UserAuthenticatedResponseDTO {
    @NotNull
    private Long id;
    private String email;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public UserAuthenticatedResponseDTO(AppUserEntity user){
        this.id = user.getId();
        this.email = user.getEmail();
    }

}
