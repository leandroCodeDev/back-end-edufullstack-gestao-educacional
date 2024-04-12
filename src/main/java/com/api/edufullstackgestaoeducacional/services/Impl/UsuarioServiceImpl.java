package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.repositories.UsuarioRepository;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repository;

    @Setter
    private TokenService tokenService;

    @Setter
    private PerfilService perfilService;
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseLogin logar(RequestLogin dto) throws NotValidException {

        UsuarioEntity user = pegaPeloLogin(dto.login());
        user = pegaPeloLogin(user.getLogin());
        return new ResponseLogin(tokenService.gerarToken(user));
    }

    @Override
    public UsuarioEntity pegaUmUsuarioPeloLogin(String login) {
        return repository.findByLogin(login).orElseGet(() -> null);
    }

    @Override
    public ResponseNovoUsuario create(RequestNovoUsuario dto) {
        loginExiste(dto.login());

        UsuarioEntity user = repository.save(new UsuarioEntity(dto));
        return user.toResponseNovoUsuarioDto();
    }

    @Override
    public List<PerfilEntity> pegaTodos() {
        return List.of();
    }

    @Override
    public PerfilEntity pegaUm(Long id) {
        return null;
    }

    @Override
    public PerfilEntity pegaUm(String perfil) {
        return null;
    }


    @Override
    public boolean validaSenha(Long id, String senha) {
        return false;
    }

    private UsuarioEntity pegaPeloLogin(String login) {
        return repository.findByLogin(login).orElseThrow(() -> new NotValidException("A validação Falhou", "Login ou senha incorreto"));
    }


    private void loginExiste(String login) {
        if (repository.findByLogin(login).isPresent()) {
            throw new NotValidException("A validação Falhou", "login ja existe");
        }
    }

}
