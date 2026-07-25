package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.DTO.HabitUpdateDTO;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@Tag(name = "Hábitos do Usuario", description = "Endpoints responsaveis pela criação e gerenciamento exclusivo de Hábitos.")
public class HabitController{
    final HabitService service;

    public HabitController(HabitService service){
        this.service = service;
    }
    @Operation(summary = "Criar um Hábito")
    @PostMapping("/users/{id}/habits")
    public ResponseEntity<HabitResponseDTO> createHabit(@Parameter(description = "ID do usuario em que deseja criar o Hábito") @PathVariable Long id,@Parameter(description = "Corpo JSON completo do Hábito") @Valid @RequestBody HabitCreateDTO habit){
        Optional<HabitResponseDTO> create = service.createHabit(id, habit);
        if(create.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(create.get());
    }
    @Operation(summary = "Pegar Todos os Hábitos Existentes")
    @GetMapping("/users/habits")
    public ResponseEntity<List<@Valid HabitResponseDTO>> getAllHabits(){
        List<HabitResponseDTO> habit = service.getAllHabits();
        if (habit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(habit);
    }
    @Operation(summary = "Pegar um Hábito pelo seu ID")
    @GetMapping("/users/habits/{id}")
    public ResponseEntity<@Valid HabitResponseDTO> getHabitById(@PathVariable Long id){
        HabitResponseDTO habit = service.getHabitById(id);
        return ResponseEntity.ok(habit);
    }
    @Operation(summary = "Pegar todos os Hábitos relacionados a um usuario ao passar o ID do usuario requisitado.")
    @GetMapping("/users/{id}/habits")
    public ResponseEntity<List<@Valid HabitResponseDTO>> getAllUserHabitsByUserId(@PathVariable Long id){
        List<HabitResponseDTO> habit = service.getAllUserHabitsById(id);
        if(habit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(habit);
    }
    @Operation(summary = "Atualizar os dados de um Hábito passando o ID do mesmo, deve-se passar o JSON de todos os elementos, mesmo os que não desejamos alterar.")
    @PutMapping("/users/habits/{id}")
    public ResponseEntity<@Valid HabitResponseDTO> updateById(@PathVariable Long id, @RequestBody HabitEntity habit){
        Optional<HabitResponseDTO> returnedHabit = service.updateById(id, habit);
        if (returnedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(returnedHabit.get());

    }

    @Operation(summary = "Deletar um Hábito pelo ID do mesmo")
    @DeleteMapping("/users/habits/{id}")
    public ResponseEntity<HabitResponseDTO> deleteById(@PathVariable Long id){
        Optional<HabitResponseDTO> deletedHabit = service.deleteById(id);
        if (deletedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(deletedHabit.get());

    }


}
