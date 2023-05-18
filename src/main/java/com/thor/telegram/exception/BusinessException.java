package com.thor.telegram.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

    public BusinessException(String error) {
        this.error = error;
        this.status = BAD_REQUEST;
    }


    private String error;


    private HttpStatus status;
}

