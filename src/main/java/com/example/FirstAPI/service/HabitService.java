package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.HabitConclusionResponseDTO;
import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.exception.HabitNotFoundException;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import com.example.FirstAPI.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    final HabitRepository repository;
    final AppUserRepository userRepository;

    public HabitService(HabitRepository repository, AppUserRepository userRepository){

        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Optional<HabitResponseDTO> createHabit(Long id, HabitCreateDTO habit) {
        HabitEntity entity = new HabitEntity(habit);
        Optional<AppUserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){return Optional.empty();}
        entity.setUsuario(userEntity.get());
        repository.save(entity);

        HabitResponseDTO dto = new HabitResponseDTO(entity);
        return Optional.of(dto);
    }

    public List<HabitResponseDTO> getAllHabits() {
        return repository.findAll().stream().map(HabitResponseDTO::new).toList();
    }

    public HabitResponseDTO getHabitById(Long id) {
       HabitEntity habit = repository.findById(id).orElseThrow(HabitNotFoundException::new);
        return new HabitResponseDTO(habit);
    }

    public List<HabitResponseDTO> getAllUserHabitsById(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return repository.findAllByUsuario_id(id).stream().map(HabitResponseDTO::new).toList();


    }

    public Optional<HabitResponseDTO> updateById(Long id, HabitEntity habit) {
        HabitEntity finded = repository.findById(id).orElseThrow(HabitNotFoundException::new);  
        finded.setNome(habit.getNome());
        finded.setDescricao(habit.getDescricao());
        finded.setFrequencia(habit.getFrequencia());
        repository.save(finded);
        return repository.findById(id).map(HabitResponseDTO::new);
    }

    public Optional<HabitResponseDTO> deleteById(Long id) {
        Optional<HabitEntity> findedHabit = repository.findById(id);
        if (findedHabit.isEmpty()){return Optional.empty();}
        HabitResponseDTO dto = new HabitResponseDTO(findedHabit.get());
        repository.deleteById(id);
        return Optional.of(dto);

    }
}
