package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.AppUserEntity;
import com.example.FirstAPI.entity.HabitEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HabitCreateDTO {


    @NotBlank(message = "O nome não deve ser nulo!")
    @Size(max = 20, message = "O nome não deve ter mais de 20 caracteres!")
    private String nome;
    @Size(max = 2000, message = "Sua descrição não deve ter mais de 2000 caracteres!")
    private String descricao;
    private HabitEntity.Frequencia frequencia;
    private boolean concluidoHoje;

    public String getNome() {return nome;}

    public String getDescricao() {return descricao;}

    public HabitEntity.Frequencia getFrequencia() {return frequencia;}



    public HabitCreateDTO(){

    }

}
