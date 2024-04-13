package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;

import java.util.List;

public interface MateriaService {


    void setCursoService(CursoService cursoService);

    ResponseMateria criarMateria(RequestMateria dto);

    ResponseMateria pegaMateria(Long id);

    ResponseMateria atualizaMateria(long id, RequestMateria dto);

    void deleteMateria(Long id);

    List<ResponseMateria> pegaMaterias();


}
