package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;
import com.api.edufullstackgestaoeducacional.entities.CursoEntity;

import java.util.List;
import java.util.Optional;

public interface CursoService {


    ResponseCurso criarCurso(RequestCurso dto);

    ResponseCurso pegaCurso(Long id);

    Optional<CursoEntity> pegaCursoEntity(Long id);

    ResponseCurso atualizaCurso(long id, RequestCurso dto);

    void deleteCurso(Long id);

    List<ResponseCurso> pegaCursos();


}
