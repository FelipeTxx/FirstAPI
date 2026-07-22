package com.example.FirstAPI.DTO;

import com.example.FirstAPI.entity.HabitEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HabitUpdateDTO {
    @NotBlank
    @Size(min = 1, max = 20)
    private String nome;
    @Size(max = 2000)
    private String descricao;
    private String frequencia;
    private Boolean concluidoHoje;

    public String getNome() {return nome;}

    public String getDescricao() {return descricao;}

    public String getFrequencia() {return frequencia;}



    public HabitUpdateDTO(){

    }
}
