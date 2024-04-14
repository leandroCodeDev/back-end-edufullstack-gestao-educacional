package com.api.edufullstackgestaoeducacional.exception.erros;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class UnauthorizedException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message, List<String> errors) {
        this.errorResponse = new ErrorResponse(message, errors);
    }

    public UnauthorizedException(String message, String error) {
        log.info("cria novo UnauthorizedException");
        this.errorResponse = new ErrorResponse(message, List.of(error));

    }


    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
