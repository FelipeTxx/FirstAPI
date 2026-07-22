package com.example.FirstAPI.repository;

import com.example.FirstAPI.entity.HabitConclusionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitConclusionRepository extends JpaRepository<HabitConclusionEntity, Long> {
}
