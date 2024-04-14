package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestNota(
        @NotNull(message = "O alunoId não pode ser vazio") Long alunoId,
        @NotNull(message = "O materiaId não pode ser vazio") Long materiaId,
        @NotNull(message = "O docenteId não pode ser vazio") Long docenteId,
        @Min(value = 0, message = "O valor deve ser maior ou igual a 0.0")
        @Max(value = 10, message = "O valor deve ser menor ou igual a 10.0") Double valor
) {
}
