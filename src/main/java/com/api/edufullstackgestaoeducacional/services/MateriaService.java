package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.entities.MateriaEntity;

import java.util.Optional;

public interface MateriaService {


    void setCursoService(CursoService cursoService);

    void setTokenService(TokenService tokenService);

    ResponseMateria criarMateria(RequestMateria dto);

    ResponseMateria pegaMateria(Long id);

    Optional<MateriaEntity> pegaMateriaEntity(Long id);


    ResponseMateria atualizaMateria(long id, RequestMateria dto);

    void deleteMateria(Long id);


}
