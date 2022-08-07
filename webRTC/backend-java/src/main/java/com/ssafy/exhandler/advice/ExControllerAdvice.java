package com.ssafy.exhandler.advice;


import com.ssafy.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    // optional.get에서 null을 꺼낼 경우
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResult noSuchElementExHandler(NoSuchElementException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    // 유효성 검증 실패
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult methodArgumentNotValidExHandler(MethodArgumentNotValidException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }


    // entity에 없는 pk 삭제 요청시
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorResult emptyResultDataAccessExHandler(EmptyResultDataAccessException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    //그 외 예외처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResult badCredentialsExHandler(BadCredentialsException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    //유효성 검증
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResult bindExHandler(BindException e) {
        log.error("[exceptionHandler] ex e", e);
        return new ErrorResult("BAD", e.getMessage());
    }
}
