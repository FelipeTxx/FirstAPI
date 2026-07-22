package com.example.FirstAPI.exception;

public class HabitConclusionAlreadyConcluedException extends RuntimeException{
    public HabitConclusionAlreadyConcluedException(String mensagem){super(mensagem);}
    public HabitConclusionAlreadyConcluedException(){super("Seu Hábito já esta concluido!");}
}
