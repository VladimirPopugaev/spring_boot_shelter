package com.popugaevvn.spring_boot_shelter.exceptions;

public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}
