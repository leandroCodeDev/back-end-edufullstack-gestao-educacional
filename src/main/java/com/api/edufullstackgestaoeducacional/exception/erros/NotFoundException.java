package com.api.edufullstackgestaoeducacional.exception.erros;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NotFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
        log.info("cria novo NotFoundException");
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
