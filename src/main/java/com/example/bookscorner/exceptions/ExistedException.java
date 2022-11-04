package com.example.bookscorner.exceptions;

public class ExistedException extends RuntimeException {
//	private static final long serialVersionUID = 1L;

    public ExistedException() {
        super();
    }

    public ExistedException(String message) {
        super(message);
    }

    public ExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
