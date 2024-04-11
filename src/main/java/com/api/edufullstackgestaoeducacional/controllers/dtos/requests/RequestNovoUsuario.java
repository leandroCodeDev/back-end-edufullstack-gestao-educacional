package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestNovoUsuario(@NotEmpty(message = "O login não pode ser vazio") String login,
                                 @NotEmpty(message = "A senha não pode ser vazia") String senha,
                                 @NotNull(message = "O perfil_id não pode ser nulo")
                                 @Min(value = 1, message = "O perfil_id deve ser maior ou igual a 1")
                                 @Max(value = 5, message = "O perfil_id deve ser menor ou igual a 5") Long perfilId) {
}
