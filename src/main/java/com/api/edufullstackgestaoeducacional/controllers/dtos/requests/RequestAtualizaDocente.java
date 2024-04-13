package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RequestAtualizaDocente(
        @NotEmpty(message = "O nome não pode ser vazio") String nome,
        @NotNull(message = "A data de entrada não pode ser vazia") Date dataEntrada,
        @NotEmpty(message = "O login não pode ser vazio") String login) {

}
