package com.popugaevvn.spring_boot_shelter.api.exceptions;

public class AuthorizedErrorException extends RuntimeException {

    public AuthorizedErrorException(String message) {
        super(message);
    }

}
