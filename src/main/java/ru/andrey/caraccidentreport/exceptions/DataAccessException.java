package ru.andrey.caraccidentreport.exceptions;

public class DataAccessException extends RuntimeException {

    public DataAccessException (String message, Exception e) {
        super(message, e);
    }
    public DataAccessException (String message) {
        super(message);
    }
}
