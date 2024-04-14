package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.entities.CursoEntity;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    void setTokenService(TokenService tokenService);

    ResponseCurso criarCurso(RequestCurso dto, String token);

    ResponseCurso pegaCurso(Long id, String token);

    Optional<CursoEntity> pegaCursoEntity(Long id);

    ResponseCurso atualizaCurso(long id, RequestCurso dto, String token);

    void deleteCurso(Long id);

    List<ResponseCurso> pegaCursos(String token);

    List<ResponseMateria> pegaMateriasdoCurso(Long id, String token);
}
