package com.api.edufullstackgestaoeducacional.services;

public interface ColecaoService {

    PerfilService getPerfilService();

    UsuarioService getUsuarioService();

    TokenService getTokenService();

    SenhaService getSenhaService();
}
