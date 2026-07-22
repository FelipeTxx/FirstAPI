package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.HabitEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HabitResponseDTO {
    @NotBlank
    @Size(min = 1, max = 20)
    private String nome;
    @Size(max = 2000)
    private String descricao;
    private HabitEntity.Frequencia frequencia;
    private Boolean concluidoHoje;

    public String getNome() {return nome;}

    public String getDescricao() {return descricao;}

    public HabitEntity.Frequencia getFrequencia() {return frequencia;}



    public HabitResponseDTO(HabitEntity habit){
        this.nome = habit.getNome();
        this.descricao = habit.getDescricao();
        this.frequencia = habit.getFrequencia();

    }


}
