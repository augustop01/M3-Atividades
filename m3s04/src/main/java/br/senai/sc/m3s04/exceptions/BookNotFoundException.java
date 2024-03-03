package br.senai.sc.m3s04.exceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}
