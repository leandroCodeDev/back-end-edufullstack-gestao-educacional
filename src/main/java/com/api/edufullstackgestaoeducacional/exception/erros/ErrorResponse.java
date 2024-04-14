package com.api.edufullstackgestaoeducacional.exception.erros;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Data
@NoArgsConstructor
public class ErrorResponse {

    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        super();
        log.info("cria class de erroResponse");
        this.message = message;
        this.errors = errors;
    }
}