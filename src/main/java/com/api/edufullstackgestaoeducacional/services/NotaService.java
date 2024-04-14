package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.entities.NotaEntity;

import java.util.Optional;

public interface NotaService {

    void setAlunoService(AlunoService alunoService);

    void setDocenteService(DocenteService docenteService);

    void setMateriaService(MateriaService materiaService);


    ResponseNota criarNota(RequestNota dto);

    ResponseNota pegaNota(Long id);

    Optional<NotaEntity> pegaNotaEntity(Long id);

    ResponseNota atualizaNota(long id, RequestNota dto);

    void deleteNota(Long id);

}
