package com.example.FirstAPI.exception;

public class PasswordNotMatchesException extends RuntimeException{
    public PasswordNotMatchesException(String mensagem){super(mensagem);}
    public PasswordNotMatchesException(){super("Senha incorreta!");}
}
