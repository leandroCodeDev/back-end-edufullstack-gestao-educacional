package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColecaoServiceImpl implements ColecaoService {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final TokenService tokenService;

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
}
