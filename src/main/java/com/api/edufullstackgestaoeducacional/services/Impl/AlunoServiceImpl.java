package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;
import com.api.edufullstackgestaoeducacional.repositories.AlunoRepository;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.TurmaService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    @Setter
    private UsuarioService usuarioService;

    @Setter
    private TurmaService turmaService;

    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseAluno criarAluno(RequestCriaAluno dto) {
        return null;
    }

    @Override
    public ResponseAluno pegaAluno(Long id) {
        return null;
    }

    @Override
    public Optional<AlunoEntity> pegaAlunoEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseAluno atualizaAluno(long id, RequestAluno dto) {
        return null;
    }

    @Override
    public void deleteAluno(Long id) {

    }

    @Override
    public List<ResponseAluno> pegaAlunos() {
        return List.of();
    }
}
