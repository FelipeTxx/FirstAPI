package com.example.FirstAPI.exception;

public class HabitNotFoundException extends RuntimeException{
    public HabitNotFoundException(String mensagem){
        super(mensagem);
    }
    public HabitNotFoundException(){super("Habito não encontrado!");}
}
