package com.example.FirstAPI.exception;

public class HabitConclusionNotFoundException extends RuntimeException{
    public HabitConclusionNotFoundException(String mensagem){super(mensagem);}
    public HabitConclusionNotFoundException(){super("Sua solicitação não foi encontrada!");}
}
