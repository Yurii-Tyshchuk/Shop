package ru.skillsad.sad.exception;

public class ResponseTemp {
    private String message = "Ошибка";

    public ResponseTemp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
