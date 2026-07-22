package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.DTO.HabitUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.exception.HabitNotFoundException;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import com.example.FirstAPI.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.awt.image.LookupOp;
import java.util.ArrayList;
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
        List<HabitEntity> habit = repository.findAll();
        List<HabitResponseDTO> listDto = new ArrayList<>();
        for (HabitEntity i: habit){
            HabitResponseDTO dto = new HabitResponseDTO(i);
            listDto.add(dto);
        }
        return listDto;
    }

    public HabitResponseDTO getHabitById(Long id) {
       HabitEntity habit = repository.findById(id).orElseThrow(HabitNotFoundException::new);
       HabitResponseDTO dto = new HabitResponseDTO(habit);
       return dto;
    }

    public List<HabitResponseDTO> getAllUserHabitsByUserId(Long id) {
        AppUserEntity userTest = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<HabitEntity> user = userRepository.findById(id).get().getHabito();
        List<HabitResponseDTO> ListDto = new ArrayList<>();
        for(HabitEntity i: user){
            HabitResponseDTO dto = new HabitResponseDTO(i);
            ListDto.add(dto);
        }
        return ListDto;



    }

    public Optional<HabitResponseDTO> updateById(Long id, HabitEntity habit) {
        Optional<HabitEntity> finded = repository.findById(id);
        if (finded.isEmpty()){return Optional.empty();}
        HabitEntity habitAtualizado = finded.get();

        habitAtualizado.setNome(habit.getNome());
        habitAtualizado.setDescricao(habit.getDescricao());
        habitAtualizado.setFrequencia(habit.getFrequencia());


        repository.save(habitAtualizado);

        HabitResponseDTO dto = new HabitResponseDTO(habitAtualizado);
        return Optional.of(dto);


    }

    public Optional<HabitResponseDTO> deleteById(Long id) {
        Optional<HabitEntity> findedHabit = repository.findById(id);
        if (findedHabit.isEmpty()){return Optional.empty();}
        HabitResponseDTO dto = new HabitResponseDTO(findedHabit.get());
        repository.deleteById(id);
        return Optional.of(dto);
    }
}
