package com.example.FirstAPI.controller;

import com.example.FirstAPI.DTO.HabitConclusionResponseDTO;
import com.example.FirstAPI.entity.HabitConclusionEntity;
import com.example.FirstAPI.service.HabitConclusionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class HabitConclusionController {

    final HabitConclusionService service;
    public HabitConclusionController(HabitConclusionService service){

        this.service = service;
    }

    @PostMapping("/habits/{id}/complete")
    public ResponseEntity<@Valid Void> concluirHabito(@PathVariable Long id){
        Boolean conclusion = service.concluirHabito(id);
        if(!conclusion){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/habits/{id}/delete/{conclusionId}")
    public ResponseEntity<@Valid Boolean> deletarHabitoConclusion(@PathVariable long id, @PathVariable Long conclusionId){
        Boolean deleted = service.deletarHabitoConclusion(id, conclusionId);
        if(!deleted){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/habits/{id}/all")
    public ResponseEntity<List<HabitConclusionResponseDTO>> getAllConclusions(@PathVariable Long id){
        List<HabitConclusionResponseDTO> conclusion = service.getAllConclusions(id);
        if(conclusion.isEmpty()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(conclusion);
    }

    @GetMapping("/habits/{id}/{conclusionID}")
    public ResponseEntity<HabitConclusionResponseDTO> getConclusionById(@PathVariable Long id, @PathVariable Long conclusionID){
        HabitConclusionResponseDTO finded = service.getConclusionById(id, conclusionID);
        return ResponseEntity.ok(finded);

    }



}
