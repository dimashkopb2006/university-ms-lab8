package kz.iitu.university.exception;

public class ConflictException extends RuntimeException {      // -> 409
    public ConflictException(String message) { super(message); }
}
