package com.example.castfindbackend.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public <T> NotFoundByIdException(Class<T> clazz, Long id){
        super(String.format("Class %s with id = %d not found", clazz.getName(), id));
    }
}