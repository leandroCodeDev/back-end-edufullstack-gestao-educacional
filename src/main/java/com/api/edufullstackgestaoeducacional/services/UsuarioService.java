package com.api.edufullstackgestaoeducacional.services;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;

import java.util.List;

public interface UsuarioService {

    void setPerfilService(PerfilService perfilService);

    void setTokenService(TokenService tokenService);

    ResponseLogin logar(RequestLogin dto) throws NotValidException;

    List<PerfilEntity> pegaTodos();

    UsuarioEntity pegaUmUsuarioPeloLogin(String login);

    PerfilEntity pegaUm(Long id);

    PerfilEntity pegaUm(String perfil);

    ResponseNovoUsuario create(RequestNovoUsuario dto);

    boolean validaSenha(Long id, String senha);

}
