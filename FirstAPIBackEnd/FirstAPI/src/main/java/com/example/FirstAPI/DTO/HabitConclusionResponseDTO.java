package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.HabitConclusionEntity;
import com.example.FirstAPI.entity.HabitEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class HabitConclusionResponseDTO {

    public HabitConclusionResponseDTO(){}

    private LocalDate data;
    private Long id;

    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {this.data = data;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HabitConclusionResponseDTO(HabitConclusionEntity habitConclusion){
        this.data = habitConclusion.getData();
        this.id = habitConclusion.getId();

    }

}
