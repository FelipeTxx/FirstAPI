package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {






    HabitEntity findByUsuario_id(Long usuarioPego);
}
