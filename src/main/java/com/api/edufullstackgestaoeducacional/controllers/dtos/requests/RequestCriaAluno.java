package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RequestCriaAluno(
        @NotEmpty(message = "O nome não pode ser vazio") String nome,
        @NotEmpty(message = "A login não pode ser vazia") String login,
        @NotNull(message = "A data de nascimento não pode ser vazia") Date dataNascimento) {
}
