package com.api.edufullstackgestaoeducacional.controllers.dtos.requests;


import jakarta.validation.constraints.NotEmpty;

public record RequestLogin(
        @NotEmpty(message = "O login não pode ser vazio") String login,
        @NotEmpty(message = "A senha não pode ser vazia") String senha
) {
}