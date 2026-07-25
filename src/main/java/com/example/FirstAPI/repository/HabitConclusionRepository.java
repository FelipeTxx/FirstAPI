package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.HabitConclusionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitConclusionRepository extends JpaRepository<HabitConclusionEntity, Long> {

    Boolean existsByDataAndHabitId(LocalDate data, Long id);

    List<HabitConclusionEntity> findByHabitId(Long id);
}
