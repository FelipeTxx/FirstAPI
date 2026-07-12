package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long>{





    List<AppUserEntity> findByNome(String nome);
}
