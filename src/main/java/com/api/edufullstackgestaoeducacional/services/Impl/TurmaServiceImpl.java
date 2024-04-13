package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestTurma;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseTurma;
import com.api.edufullstackgestaoeducacional.entities.CursoEntity;
import com.api.edufullstackgestaoeducacional.entities.DocenteEntity;
import com.api.edufullstackgestaoeducacional.entities.TurmaEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.repositories.TurmaRepository;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.DocenteService;
import com.api.edufullstackgestaoeducacional.services.TurmaService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository repository;

    @Setter
    private CursoService cursoService;
    @Setter
    private DocenteService docenteService;

    public TurmaServiceImpl(TurmaRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseTurma criarTurma(RequestTurma dto) {

        CursoEntity curso = cursoService.pegaCursoEntity(dto.cursoId()).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        DocenteEntity docente = docenteService.pegaDocenteEntity(dto.docenteId()).orElseThrow(() -> new NotFoundException("Docente não encontrado"));

        if (!docente.getUsuario().getPerfil().getNome().equals("PROFESSOR")) {
            throw new NotFoundException("O Docente não tem papel de Professor");
        }

        TurmaEntity turma = new TurmaEntity(dto, curso, docente);
        turma = repository.save(turma);
        turma = repository.findById(turma.getId()).orElseThrow(() -> new NotFoundException("Turma não encontrado"));
        return turma.toResponseTurma();
    }

    @Override
    public ResponseTurma pegaTurma(Long id) {
        return null;
    }

    @Override
    public Optional<TurmaEntity> pegaTurmaEntity(Long id) {
        return Optional.empty();
    }

    @Override
    public ResponseTurma atualizaTurma(long id, RequestTurma dto) {
        return null;
    }

    @Override
    public void deleteTurma(Long id) {

    }

    @Override
    public List<ResponseTurma> pegaTurmas() {
        return List.of();
    }

}
