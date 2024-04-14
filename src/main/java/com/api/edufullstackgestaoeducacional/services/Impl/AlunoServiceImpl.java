package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;
import com.api.edufullstackgestaoeducacional.entities.TurmaEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.repositories.AlunoRepository;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.TurmaService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin(dto.login());
        TurmaEntity turma = turmaService.pegaTurmaEntity(dto.turmaId()).orElseThrow(() -> new NotFoundException("Turma não encontrado"));

        if (!dto.dataNascimento().before(new Date())) {
            throw new NotValidException("A validação Falhou", "A dataNascimento não pode ser uma data futura");
        }

        if (user == null) {
            throw new NotValidException("A validação Falhou", "login de usuario não existe");
        }
        exiteUsuario(user.getId());
        AlunoEntity aluno = new AlunoEntity(dto, user, turma);
        aluno = repository.save(aluno);
        aluno = repository.findById(aluno.getId()).orElseThrow(() -> new NotFoundException("Turma não encontrado"));

        return aluno.toResponseAluno();
    }

    @Override
    public ResponseAluno pegaAluno(Long id) {
        AlunoEntity aluno = pegaAlunoEntity(id).orElseThrow(() -> new NotFoundException("Turma não encontrado"));
        return aluno.toResponseAluno();
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

    private void exiteUsuario(Long id) {
        if (repository.findByUsuarioId(id).isPresent()) {
            throw new NotValidException("A validação falhou!", "Usuario ja vinculado a outro aluno!");
        }
    }


    private void exiteUsuario(Long idAluno, Long idUsuario) {
        Optional<AlunoEntity> aluno = repository.findByUsuarioId(idUsuario);
        if (aluno.isPresent() && aluno.get().getId() != idAluno) {
            throw new NotValidException("A validação falhou!", "Usuario ja vinculado a outro aluno!");
        }
    }
}
