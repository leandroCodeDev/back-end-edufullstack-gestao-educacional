package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestMateria(
        @NotEmpty(message = "O nome não pode ser vazio") String nome,
        @NotNull(message = "O cursoId  não pode ser vazio") Long cursoId
) {
}
