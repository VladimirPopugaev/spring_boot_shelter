package com.popugaevvn.spring_boot_shelter.controllers;

import com.popugaevvn.spring_boot_shelter.api.response.ErrorResponse;
import com.popugaevvn.spring_boot_shelter.api.exceptions.AuthorizedErrorException;
import com.popugaevvn.spring_boot_shelter.api.exceptions.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NotFoundEntityException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handle(AuthorizedErrorException exception) {
        return new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

}
