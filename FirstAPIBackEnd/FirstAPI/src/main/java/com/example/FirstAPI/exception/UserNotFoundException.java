package com.example.FirstAPI.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String mensagem){
        super(mensagem);
    }
    public UserNotFoundException(){
        super("Usuario não encontrado!!!");
    }
}
