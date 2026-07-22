package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.DTO.HabitUpdateDTO;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


public class HabitController{
    final HabitService service;

    public HabitController(HabitService service){
        this.service = service;
    }
    @PostMapping("/users/{id}/habits")
    public ResponseEntity<HabitResponseDTO> createHabit(@PathVariable Long id, @Valid @RequestBody HabitCreateDTO habit){
        Optional<HabitResponseDTO> create = service.createHabit(id, habit);
        if(create.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(create.get());
    }

    @GetMapping("/users/habits")
    public ResponseEntity<List<@Valid HabitResponseDTO>> getAllHabits(){
        List<HabitResponseDTO> habit = service.getAllHabits();
        if (habit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(habit);
    }

    @GetMapping("/users/habits/{id}")
    public ResponseEntity<@Valid HabitResponseDTO> getHabitById(@PathVariable Long id){
        HabitResponseDTO habit = service.getHabitById(id);
        return ResponseEntity.ok(habit);
    }

    @GetMapping("/users/{id}/habits")
    public ResponseEntity<List<@Valid HabitResponseDTO>> getAllUserHabitsByUserId(@PathVariable Long id){
        List<HabitResponseDTO> habit = service.getAllUserHabitsByUserId(id);
        if(habit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(habit);
    }

    @PutMapping("/users/habits/{id}")
    public ResponseEntity<@Valid HabitResponseDTO> updateById(@PathVariable Long id, @RequestBody HabitEntity habit){
        Optional<HabitResponseDTO> returnedHabit = service.updateById(id, habit);
        if (returnedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(returnedHabit.get());

    }


    @DeleteMapping("/users/habits/{id}")
    public ResponseEntity<HabitResponseDTO> deleteById(@PathVariable Long id){
        Optional<HabitResponseDTO> deletedHabit = service.deleteById(id);
        if (deletedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(deletedHabit.get());

    }


}
