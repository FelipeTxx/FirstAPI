package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.UserAuthenticatedResponseDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    final AppUserRepository userRepository;

    public CurrentUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAuthenticatedResponseDTO getAuthenticatedUser(){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        return new UserAuthenticatedResponseDTO(userRepository.findByEmail(user).orElseThrow(UserNotFoundException::new));
    }



}
