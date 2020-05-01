package com.example.photoapp.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer HttpStatus;
    private String message;
    public ErrorResponse(Integer HttpStatus, String message) {
        this.HttpStatus = HttpStatus;
        this.message = message;
    }
}
