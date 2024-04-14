package com.api.edufullstackgestaoeducacional.exception.erros;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class NotValidException extends RuntimeException {
    private ErrorResponse errorResponse;

    public NotValidException() {
    }

    public NotValidException(String message, List<String> errors) {
        this.errorResponse = new ErrorResponse(message, errors);
    }

    public NotValidException(String message, String error) {
        log.info("cria novo NotFoundException");
        this.errorResponse = new ErrorResponse(message, List.of(error));

    }


    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidException(Throwable cause) {
        super(cause);
    }

    public NotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
