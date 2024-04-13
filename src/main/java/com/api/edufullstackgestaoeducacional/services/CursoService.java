package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAtualizaCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaCurso;

import java.util.List;

public interface CursoService {


    ResponseCriarCurso criarCurso(RequestCriarCurso dto);

    ResponsePegaCurso pegaCurso(Long id);

    ResponseAtualizaCurso atualizaCurso(long id, RequestAtualizaCurso dto);

    void deleteCurso(Long id);

    List<ResponsePegaCurso> pegaCurso();
}
