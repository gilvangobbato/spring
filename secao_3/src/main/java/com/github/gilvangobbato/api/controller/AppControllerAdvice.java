package com.github.gilvangobbato.api.controller;

import com.github.gilvangobbato.api.ApiErrors;
import com.github.gilvangobbato.exception.RegraException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(RegraException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraException ex){
        return new ApiErrors(ex.getMessage());
    }
}
