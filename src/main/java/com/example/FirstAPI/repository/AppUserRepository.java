package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long>{


}
