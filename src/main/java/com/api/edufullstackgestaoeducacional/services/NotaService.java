package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.entities.NotaEntity;

import java.util.Optional;

public interface NotaService {

    void setAlunoService(AlunoService alunoService);

    void setDocenteService(DocenteService docenteService);

    void setMateriaService(MateriaService materiaService);

    void setTokenService(TokenService tokenService);


    ResponseNota criarNota(RequestNota dto, String token);

    ResponseNota pegaNota(Long id, String token);

    Optional<NotaEntity> pegaNotaEntity(Long id);

    ResponseNota atualizaNota(long id, RequestNota dto, String token);

    void deleteNota(Long id);

}
