package ru.hse.javaprogramming;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
