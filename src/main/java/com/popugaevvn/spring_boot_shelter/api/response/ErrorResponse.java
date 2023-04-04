package com.popugaevvn.spring_boot_shelter.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final HttpStatus status;
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    public ErrorResponse(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
