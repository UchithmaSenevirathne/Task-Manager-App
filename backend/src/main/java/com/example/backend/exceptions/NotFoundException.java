package com.example.backend.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {}
    public NotFoundException(String message) {}
    public NotFoundException(String message, Throwable cause) {}
}
