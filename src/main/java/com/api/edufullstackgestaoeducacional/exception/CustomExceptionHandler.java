package com.api.edufullstackgestaoeducacional.exception;

import com.api.edufullstackgestaoeducacional.exception.erros.ErrorResponse;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.exception.erros.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que lida com exceções globais na aplicação.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método para lidar com exceções de validação de argumento de método não válido.
     *
     * @param ex      A exceção MethodArgumentNotValidException que foi lançada.
     * @param headers Os cabeçalhos HTTP da resposta.
     * @param status  O status HTTP da resposta.
     * @param request O objeto WebRequest que representa a solicitação.
     * @return Uma resposta ResponseEntity contendo uma lista de erros de validação.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // Lista para armazenar os erros de validação
        List<String> errors = new ArrayList<>();

        // Itera sobre todos os erros de validação e adiciona à lista de erros
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        // Cria uma instância de ErrorResponse contendo a mensagem de erro e a lista de erros
        ErrorResponse errorResponse = new ErrorResponse("A validação falhou!", errors);

        // Retorna uma resposta ResponseEntity com a ErrorResponse e o status HTTP Bad Request

        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Object> handler(NotValidException e) {
        return new ResponseEntity(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handler(UnauthorizedException e) {
        return new ResponseEntity(e.getErrorResponse(), HttpStatus.UNAUTHORIZED);
    }
}