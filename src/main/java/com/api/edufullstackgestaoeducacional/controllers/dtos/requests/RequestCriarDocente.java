package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.NotEmpty;

public record RequestCriarDocente(
        @NotEmpty(message = "O nome não pode ser vazio") String nome,
        @NotEmpty(message = "A login não pode ser vazia") String login
) {
}
