package com.converter.converter.controller;

public class ApiError {

    public ApiError(String message){
        this.message = message;
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
