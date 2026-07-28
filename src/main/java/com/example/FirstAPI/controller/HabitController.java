package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.DTO.HabitUpdateDTO;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.service.CurrentUserService;
import com.example.FirstAPI.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.tomcat.util.descriptor.web.ContextService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PostMapping("/users/me/habits")
    public ResponseEntity<HabitResponseDTO> createHabit(@Parameter(description = "Corpo JSON completo do Hábito") @Valid @RequestBody HabitCreateDTO habit){


        Optional<HabitResponseDTO> create = service.createHabit(habit);
        if(create.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(create.get());
    }
    @Operation(summary = "Pegar todos os Hábitos relacionados a um usuario.")
    @GetMapping("/users/me/habits")
    public ResponseEntity<List<@Valid HabitResponseDTO>> getAllUserHabitsByUserId(){
        List<HabitResponseDTO> habit = service.getAllUserHabitsById();
        if(habit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(habit);
    }
    @Operation(summary = "Atualizar os dados de um Hábito passando o ID do mesmo, deve-se passar o JSON de todos os elementos, mesmo os que não desejamos alterar.")
    @PutMapping("/users/me/habits/{habitId}")
    public ResponseEntity<@Valid HabitResponseDTO> updateById(@PathVariable Long habitId, @RequestBody HabitUpdateDTO habit){
        Optional<HabitResponseDTO> returnedHabit = service.updateById(habitId ,habit);
        if (returnedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(returnedHabit.get());

    }

    @Operation(summary = "Deletar um Hábito pelo ID do mesmo")
    @DeleteMapping("/users/me/habits/{habitId}")
    public ResponseEntity<HabitResponseDTO> deleteById(@PathVariable Long habitId){
        Optional<HabitResponseDTO> deletedHabit = service.deleteById(habitId);
        if (deletedHabit.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(deletedHabit.get());

    }

    @Operation(summary = "Mostrar o progresso da sua meta")
    @GetMapping("/users/me/habits/{habitId}/meta")
    public ResponseEntity<Double> getMetaProgression(@PathVariable Long habitId){
        return ResponseEntity.ok(service.getMetaProgression(habitId));
    }


}
