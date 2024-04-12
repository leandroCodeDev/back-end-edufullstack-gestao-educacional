package com.api.edufullstackgestaoeducacional.services;

import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;

public interface TokenService {

    String gerarToken(UsuarioEntity user);

    String buscaCampo(String token, String claim);

    void validateAdmin(String token);

    void validatePedadogico(String token);

    void validateRecruiter(String token);

    void validateProfessor(String token);

    void validateAluno(String token);
}

