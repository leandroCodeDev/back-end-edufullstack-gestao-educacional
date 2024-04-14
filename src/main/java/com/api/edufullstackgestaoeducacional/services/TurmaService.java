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

    ResponseTurma criarTurma(RequestTurma dto, String token);

    ResponseTurma pegaTurma(Long id, String token);

    Optional<TurmaEntity> pegaTurmaEntity(Long id);

    ResponseTurma atualizaTurma(long id, RequestTurma dto, String token);

    void deleteTurma(Long id);

    List<ResponseTurma> pegaTurmas(String token, String s);
}
