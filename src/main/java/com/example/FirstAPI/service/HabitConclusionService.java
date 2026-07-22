package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.HabitConclusionResponseDTO;
import com.example.FirstAPI.entity.HabitConclusionEntity;
import com.example.FirstAPI.entity.HabitEntity;
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

    public HabitConclusionService(HabitConclusionRepository repository, HabitRepository habitRepository) {
        this.repository = repository;
        this.habitRepository = habitRepository;
    }


    public Boolean concluirHabito(Long id) {
        HabitEntity findedHabito = habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        LocalDate dataAgora = LocalDate.now();

        HabitConclusionEntity habitConclusion = new HabitConclusionEntity();

        List<HabitConclusionEntity> habitData = repository.findAll();

        habitConclusion.setHabit(findedHabito);



        for (HabitConclusionEntity i : habitData) {

            if (Objects.equals(i.getHabit().getId(), id)) {
                if (i.getData().equals(dataAgora)) {
                    throw new HabitConclusionAlreadyConcluedException();
                }
            }
        }

        habitConclusion.setHabit(findedHabito);
        habitConclusion.setData(dataAgora);
        repository.save(habitConclusion);
        return true;

    }

    public Boolean deletarHabitoConclusion(long id, Long conclusionId) {
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
        HabitEntity findedHabit = habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        System.out.println(findedHabit.getHabitConclusion().size());
        System.out.println(repository.findAll().size());
        List<HabitConclusionResponseDTO> listDTO = new ArrayList<>();
        HabitConclusionEntity habitConclusion = new HabitConclusionEntity();
        habitConclusion.setHabit(findedHabit);
        for (HabitConclusionEntity i: findedHabit.getHabitConclusion()){
            HabitConclusionResponseDTO dto = new HabitConclusionResponseDTO(i);
            listDTO.add(dto);
        }
        if (listDTO.isEmpty()){ throw new HabitConclusionNotFoundException("Esse habito não tem nenhuma conclusao!");}
        return listDTO;

    }

    public HabitConclusionResponseDTO getConclusionById(Long id, Long conclusionID) {
        habitRepository.findById(id).orElseThrow(HabitNotFoundException::new);
        HabitConclusionEntity findedConclusion = repository.findById(conclusionID).orElseThrow(HabitConclusionNotFoundException::new);
        HabitConclusionResponseDTO dto = new HabitConclusionResponseDTO(findedConclusion);
        return dto;
    }
}