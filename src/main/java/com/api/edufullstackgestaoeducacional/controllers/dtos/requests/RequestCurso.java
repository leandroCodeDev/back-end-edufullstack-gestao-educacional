package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.NotEmpty;

public record RequestCurso(
        @NotEmpty(message = "O nome não pode ser vazio") String nome
) {
}
