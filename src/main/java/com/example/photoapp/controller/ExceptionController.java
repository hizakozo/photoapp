package com.example.photoapp.controller;

import com.example.photoapp.exception.*;
import com.example.photoapp.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({NotFoundDataException.class})
    @ResponseBody
    public ErrorResponse NotFoundError() {
        return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Not Found Data");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({DifferencePasswordException.class})
    @ResponseBody
    public ErrorResponse passwordError() {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Password authentication failed");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({TokenException.class})
    @ResponseBody
    public ErrorResponse tokenError() {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Token authentication failed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ErrorResponse validationError() {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Contradicts validation rules");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({UniqueException.class})
    @ResponseBody
    public ErrorResponse uniqueError() {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), "Data that already exists");
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler({FileUploadException.class})
    @ResponseBody
    public ErrorResponse fileUpError() {
        return new ErrorResponse(HttpStatus.PRECONDITION_FAILED.value(), "File upload failed");
    }
}