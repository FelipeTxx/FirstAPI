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
    private HabitEntity.Frequencia frequencia;
    private Boolean concluidoHoje;

    public String getNome() {return nome;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HabitEntity.Frequencia getFrequencia() {return frequencia;}

    public void setFrequencia(HabitEntity.Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public HabitUpdateDTO(){

    }
}
