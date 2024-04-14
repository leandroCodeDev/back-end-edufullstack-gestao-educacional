package com.api.edufullstackgestaoeducacional.services;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;

import java.util.List;

public interface UsuarioService {

    void setPerfilService(PerfilService perfilService);

    void setTokenService(TokenService tokenService);

    void setSenhaService(SenhaService senhaService);


    ResponseLogin logar(RequestLogin dto) throws NotValidException;

    ResponseNovoUsuario cadastrar(RequestNovoUsuario dto);


    List<UsuarioEntity> pegaTodos();

    UsuarioEntity pegaUmUsuarioPeloLogin(String login);

    UsuarioEntity pegaUm(Long id);

    UsuarioEntity pegaUm(String perfil);


    void validaSenha(Long id, String senha);

    void validaSenha(String senha, String senhaEncriptada);

}
