package com.api.edufullstackgestaoeducacional.exception.erros;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponse {

    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> errors;

    public ErrorResponse(String message, List<String> errors) {
        super();
        this.message = message;
        this.errors = errors;
    }
}