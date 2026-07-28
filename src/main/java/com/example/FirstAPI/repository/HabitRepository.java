package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
    List<HabitEntity> findAllByUsuario_id(Long id);








    /*HabitEntity findByUsuarioId(Long usuarioPego);

    List<AppUserEntity> findByUserId();*/
}
