package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.exception.erros.UnauthorizedException;
import com.api.edufullstackgestaoeducacional.repositories.UsuarioRepository;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import com.api.edufullstackgestaoeducacional.services.SenhaService;
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
    private SenhaService senhaService;

    @Setter
    private PerfilService perfilService;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseLogin logar(RequestLogin dto) throws NotValidException {

        UsuarioEntity user = pegaPeloLogin(dto.login());
        user = pegaPeloLogin(user.getLogin());
        validaSenha(dto.senha(), user.getSenha());
        return new ResponseLogin(tokenService.gerarToken(user));
    }

    @Override
    public ResponseNovoUsuario cadastrar(RequestNovoUsuario dto) {


        loginExiste(dto.login());
        UsuarioEntity user = new UsuarioEntity(dto);
        user.setSenha(senhaService.encriptSenha(user.getSenha()));
        repository.save(user);
        user = pegaPeloLogin(user.getLogin());
        return user.toResponseNovoUsuarioDto();
    }

    @Override
    public UsuarioEntity pegaUmUsuarioPeloLogin(String login) {
        return repository.findByLogin(login).orElseGet(() -> null);
    }



    @Override
    public void validaSenha(Long id, String senha) {
        UsuarioEntity user = repository.findById(id).orElseThrow(() -> new UnauthorizedException("A validação Falhou", "Login ou senha incorreto"));
        if (!senhaService.compararSenha(senha, user.getSenha())) {
            throw new UnauthorizedException("A validação Falhou", "Login ou senha incorreto");
        }
    }

    @Override
    public void validaSenha(String senha, String senhaEncriptada) {
        if (!senhaService.compararSenha(senha, senhaEncriptada)) {
            throw new UnauthorizedException("A validação Falhou", "Login ou senha incorreto");
        }
    }


    @Override
    public List<UsuarioEntity> pegaTodos() {
        return List.of();
    }

    @Override
    public UsuarioEntity pegaUm(Long id) {
        return null;
    }

    @Override
    public UsuarioEntity pegaUm(String perfil) {
        return null;
    }

    private UsuarioEntity pegaPeloLogin(String login) {
        return repository.findByLogin(login).orElseThrow(() -> new UnauthorizedException("A validação Falhou", "Login ou senha incorreto"));
    }


    private void loginExiste(String login) {
        if (repository.findByLogin(login).isPresent()) {
            throw new NotValidException("A validação Falhou", "login ja existe");
        }
    }

}
