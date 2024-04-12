package com.api.edufullstackgestaoeducacional.services;

import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;

public interface TokenService {

    String gerarToken(UsuarioEntity user);

    String buscaCampo(String token, String claim);
}
