package com.example.FirstAPI.entity;

import com.example.FirstAPI.DTO.HabitCreateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class HabitEntity {
    public HabitEntity(){}

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String descricao;

    public enum Frequencia{
        DIARIO, SEMANAL, MENSAL
    }
    @Enumerated(EnumType.STRING)
    private Frequencia frequencia;
    @OneToMany(mappedBy = "habit")
    private List<HabitConclusionEntity> habitConclusion;

    //talvez um @ForeignKey ou algo do genero?
    @ManyToOne
    private AppUserEntity usuario;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public Frequencia getFrequencia() {return frequencia;}
    public void setFrequencia(Frequencia frequencia) {this.frequencia = frequencia;}



    public AppUserEntity getUsuario() {return usuario;}
    public void setUsuario(AppUserEntity usuario) {this.usuario = usuario;}

    public List<HabitConclusionEntity> getHabitConclusion() {return habitConclusion;}

    public void setHabitConclusion(List<HabitConclusionEntity> habitConclusion) {this.habitConclusion = habitConclusion;}

    public HabitEntity(HabitCreateDTO dto){
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.frequencia = dto.getFrequencia();

    }

}
