package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAtualizaDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAtualizaDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaDocente;
import com.api.edufullstackgestaoeducacional.entities.DocenteEntity;

import java.util.List;
import java.util.Optional;

public interface DocenteService {


    void setTokenService(TokenService tokenService);

    void setUsuarioService(UsuarioService usuarioService);

    ResponseCriarDocente criarDocente(RequestCriarDocente dto);

    ResponsePegaDocente pegaDocente(Long id);

    Optional<DocenteEntity> pegaDocenteEntity(Long id);

    ResponseAtualizaDocente atualizaDocente(long id, RequestAtualizaDocente dto);

    void deleteDocente(Long id);


    List<ResponsePegaDocente> pegaDocentes();
}
