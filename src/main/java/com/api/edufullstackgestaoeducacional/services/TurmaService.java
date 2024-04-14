package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestTurma;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseTurma;
import com.api.edufullstackgestaoeducacional.entities.TurmaEntity;

import java.util.List;
import java.util.Optional;

public interface TurmaService {

    void setCursoService(CursoService cursoService);

    void setDocenteService(DocenteService docenteService);

    void setTokenService(TokenService tokenService);

    ResponseTurma criarTurma(RequestTurma dto);

    ResponseTurma pegaTurma(Long id);

    Optional<TurmaEntity> pegaTurmaEntity(Long id);

    ResponseTurma atualizaTurma(long id, RequestTurma dto);

    void deleteTurma(Long id);

    List<ResponseTurma> pegaTurmas();
}
