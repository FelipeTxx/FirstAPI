package com.example.FirstAPI.exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String mensagem){
        super(mensagem);
    }
    public EmailNotFoundException(){
        super("O email não foi encontrado!!!");
    }
}
