package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.HabitConclusionResponseDTO;
import com.example.FirstAPI.entity.HabitConclusionEntity;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.exception.AccessDeniedException;
import com.example.FirstAPI.exception.HabitConclusionAlreadyConcluedException;
import com.example.FirstAPI.exception.HabitConclusionNotFoundException;
import com.example.FirstAPI.exception.HabitNotFoundException;
import com.example.FirstAPI.repository.HabitConclusionRepository;
import com.example.FirstAPI.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HabitConclusionService {

    final HabitConclusionRepository repository;
    final HabitRepository habitRepository;
    final CurrentUserService currentUserService;

    public HabitConclusionService(HabitConclusionRepository repository, HabitRepository habitRepository, CurrentUserService currentUserService) {
        this.repository = repository;
        this.habitRepository = habitRepository;
        this.currentUserService = currentUserService;
    }


    private void authorizarUsoHabit(Long id){
        if (!(habitRepository.findById(id).orElseThrow(HabitNotFoundException::new).getUsuario().getId().equals(currentUserService.getAuthenticatedUser().getId()))){
            throw new AccessDeniedException();
        }
    }


    public Boolean concluirHabito(Long id) {
        authorizarUsoHabit(id);
        HabitEntity findedHabito = habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        LocalDate dataAgora = LocalDate.now();
        HabitConclusionEntity habitConclusion = new HabitConclusionEntity();
        if(repository.existsByDataAndHabitId(dataAgora, id)){throw new HabitConclusionAlreadyConcluedException();}
        habitConclusion.setHabit(findedHabito);
        habitConclusion.setData(dataAgora);
        repository.save(habitConclusion);
        return true;
    }

    public Boolean deletarHabitoConclusion(long id, Long conclusionId) {
        authorizarUsoHabit(id);
        if (habitRepository.findById(id).isEmpty()) {
            throw new HabitNotFoundException();
        }
        if (repository.findById(conclusionId).isEmpty()) {
            throw new HabitConclusionNotFoundException();
        }
        repository.deleteById(conclusionId);
        return true;
    }

    public List<HabitConclusionResponseDTO> getAllConclusions(Long id) {
        authorizarUsoHabit(id);
        habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        return repository.findByHabitId(id).stream().map(HabitConclusionResponseDTO::new).toList();
    }

    public HabitConclusionResponseDTO getConclusionById(Long id, Long conclusionID) {
        authorizarUsoHabit(id);
        habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        HabitConclusionEntity findedConclusion = repository.findById(conclusionID).orElseThrow(HabitConclusionNotFoundException::new);
        HabitConclusionResponseDTO dto = new HabitConclusionResponseDTO(findedConclusion);
        return dto;
    }

    public HabitConclusionResponseDTO getConclusionByData(Long id, LocalDate data) {
        authorizarUsoHabit(id);
        habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        HabitConclusionEntity findedConclusion = repository.findByHabitIdAndData(id, data).orElseThrow(HabitConclusionNotFoundException::new);
        HabitConclusionResponseDTO dto = new HabitConclusionResponseDTO(findedConclusion);
        return dto;
    }
}