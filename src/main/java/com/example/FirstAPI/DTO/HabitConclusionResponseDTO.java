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

    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {this.data = data;}

    public HabitConclusionResponseDTO(HabitConclusionEntity habitConclusion){
        this.data = habitConclusion.getData();

    }

}
