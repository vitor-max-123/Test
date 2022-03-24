package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public enum ResultStatus {
    SUCCESS(HttpStatus.OK,200,"OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),;

    private HttpStatus httpStatus;

    private Integer code;

    private String message;
}
