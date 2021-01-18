package ru.skillsad.sad.exception;

public class NotUniqueEntity extends RuntimeException{
    public NotUniqueEntity(String message) {
        super(message);
    }
}
