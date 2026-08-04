package com.example.FirstAPI.service;

import com.example.FirstAPI.DTO.HabitConclusionResponseDTO;
import com.example.FirstAPI.DTO.HabitCreateDTO;
import com.example.FirstAPI.DTO.HabitResponseDTO;
import com.example.FirstAPI.DTO.HabitUpdateDTO;
import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.entity.HabitConclusionEntity;
import com.example.FirstAPI.entity.HabitEntity;
import com.example.FirstAPI.exception.AccessDeniedException;
import com.example.FirstAPI.exception.HabitNotFoundException;
import com.example.FirstAPI.exception.UserNotFoundException;
import com.example.FirstAPI.repository.AppUserRepository;
import com.example.FirstAPI.repository.HabitConclusionRepository;
import com.example.FirstAPI.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HabitService {

    final HabitRepository repository;
    final AppUserRepository userRepository;
    final CurrentUserService currentUserService;
    final HabitConclusionRepository habitConclusionRepository;

    public HabitService(HabitRepository repository, AppUserRepository userRepository, CurrentUserService currentUserService, HabitConclusionRepository habitConclusionRepository){

        this.repository = repository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.habitConclusionRepository = habitConclusionRepository;
    }

    private void autorizarUso(long habitId){
        HabitEntity habit = repository.findById(habitId).orElseThrow(HabitNotFoundException::new);
        if (!Objects.equals(habit.getUsuario().getId(), currentUserService.getAuthenticatedUser().getId())){ throw new AccessDeniedException();}
    }




    public Optional<HabitResponseDTO> createHabit(HabitCreateDTO habit) {

        Long id = currentUserService.getAuthenticatedUser().getId();

        HabitEntity entity = new HabitEntity(habit);
        Optional<AppUserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){return Optional.empty();}
        entity.setUsuario(userEntity.get());
        repository.save(entity);

        HabitResponseDTO dto = new HabitResponseDTO(entity);
        return Optional.of(dto);
    }


    public List<HabitResponseDTO> getAllUserHabitsById() {

        Long id = currentUserService.getAuthenticatedUser().getId();

        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return repository.findAllByUsuario_id(id).stream().map(HabitResponseDTO::new).toList();


    }

    public Optional<HabitResponseDTO> updateById(Long habitId , HabitUpdateDTO habit) {
        HabitEntity finded = repository.findById(habitId).orElseThrow(HabitNotFoundException::new);
        autorizarUso(habitId);
        finded.setNome(habit.getNome());
        finded.setDescricao(habit.getDescricao());
        finded.setFrequencia(habit.getFrequencia());
        finded.setMeta(habit.getMeta());
        repository.save(finded);
        return repository.findById(habitId).map(HabitResponseDTO::new);
    }

    public Optional<HabitResponseDTO> deleteById(Long habitId) {
        autorizarUso(habitId);
        Optional<HabitEntity> findedHabit = repository.findById(habitId);
        if (findedHabit.isEmpty()){return Optional.empty();}
        HabitResponseDTO dto = new HabitResponseDTO(findedHabit.get());
        repository.deleteById(habitId);
        return Optional.of(dto);

    }


    public double getMetaProgression(Long habitId){
        Long id = currentUserService.getAuthenticatedUser().getId();
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        autorizarUso(habitId);
        HabitConclusionEntity firstData = habitConclusionRepository.findFirstByHabitIdOrderByDataAsc(habitId);
        HabitConclusionEntity lastData = habitConclusionRepository.findLastByHabitIdOrderByDataAsc(habitId);
        HabitEntity meta = repository.findById(habitId).orElseThrow(() ->{throw new HabitNotFoundException("Você não possui uma meta cadastrada!");});
        double diasTotais = ChronoUnit.DAYS.between(firstData.getData(), meta.getMeta())+1;
        double diasDesdeDaUltimaMarcacao = ChronoUnit.DAYS.between(firstData.getData(), lastData.getData())+1;

        return ((diasDesdeDaUltimaMarcacao/diasTotais)*100);

    }



}
