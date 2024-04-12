package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColecaoServiceImpl implements ColecaoService {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final TokenService tokenService;
    private final SenhaService senhaService;

    @Override
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    @Override
    public PerfilService getPerfilService() {
        return perfilService;
    }

    @Override
    public TokenService getTokenService() {
        return tokenService;
    }

    @Override
    public SenhaService getSenhaService() {
        return senhaService;
    }
}
