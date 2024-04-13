package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;

import java.util.List;

public interface CursoService {


    ResponseCurso criarCurso(RequestCurso dto);

    ResponseCurso pegaCurso(Long id);

    ResponseCurso atualizaCurso(long id, RequestCurso dto);

    void deleteCurso(Long id);

    List<ResponseCurso> pegaCursos();


}
