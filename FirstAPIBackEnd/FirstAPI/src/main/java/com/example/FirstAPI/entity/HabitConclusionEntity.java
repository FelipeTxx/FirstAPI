package com.example.FirstAPI.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HabitConclusionEntity {
    public HabitConclusionEntity(){}

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate data;


    @ManyToOne
    private HabitEntity habit;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getData() {return data;}
    public void setData(LocalDate data) {this.data = data;}

    public HabitEntity getHabit() {
        return habit;
    }

    public void setHabit(HabitEntity habit) {
        this.habit = habit;
    }
}
