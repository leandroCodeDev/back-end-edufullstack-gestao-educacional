package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaDocente;
import com.api.edufullstackgestaoeducacional.entities.DocenteEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.repositories.DocenteRepository;
import com.api.edufullstackgestaoeducacional.services.DocenteService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository repository;

    @Setter
    private UsuarioService usuarioService;

    @Setter
    private TokenService tokenService;


    public DocenteServiceImpl(DocenteRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseCriarDocente criarDocente(RequestCriarDocente dto) {
        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin(dto.login());
        exiteUsuario(user.getId());
        DocenteEntity docente = repository.save(new DocenteEntity(dto, user));
        docente = repository.findById(docente.getId()).orElseThrow(() -> new NotValidException("A validação falhou!", "Docente não encontrado"));
        return docente.toResponseCriarDocente();
    }

    @Override
    public ResponsePegaDocente pegaDocente(Long id) {
        DocenteEntity docente = repository.findById(id).orElseThrow(() -> new NotFoundException("Docente não encontrado"));
        return docente.toResponsePegaDocente();
    }


    private void exiteUsuario(Long id) {
        if (repository.findByUsuarioId(id).isPresent()) {
            throw new NotValidException("A validação falhou!", "Usuario ja vinculado a um docente!");
        }
    }
}
