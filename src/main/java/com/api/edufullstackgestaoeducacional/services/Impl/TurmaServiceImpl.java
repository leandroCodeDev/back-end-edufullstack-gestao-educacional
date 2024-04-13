package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestTurma;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseTurma;
import com.api.edufullstackgestaoeducacional.entities.TurmaEntity;
import com.api.edufullstackgestaoeducacional.repositories.CursoRepository;
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
        return null;
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
