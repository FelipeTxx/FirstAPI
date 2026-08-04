package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long>{


    Optional<AppUserEntity> findByEmail(String email);
}
