package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAtualizaCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaCurso;
import com.api.edufullstackgestaoeducacional.repositories.CursoRepository;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.ResponseAtualizaCurso;
import com.api.edufullstackgestaoeducacional.services.ResponseCriarCurso;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;


    @Override
    public ResponseCriarCurso criarCurso(RequestCriarCurso dto) {
        return null;
    }

    @Override
    public ResponsePegaCurso pegaCurso(Long id) {
        return null;
    }

    @Override
    public ResponseAtualizaCurso atualizaCurso(long id, RequestAtualizaCurso dto) {
        return null;
    }

    @Override
    public void deleteCurso(Long id) {

    }

    @Override
    public List<ResponsePegaCurso> pegaCurso() {
        return List.of();
    }
}
