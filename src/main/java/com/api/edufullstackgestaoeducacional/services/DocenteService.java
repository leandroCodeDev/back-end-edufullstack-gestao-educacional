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

    ResponseCriarDocente criarDocente(RequestCriarDocente dto, String token);

    ResponsePegaDocente pegaDocente(Long id, String token);

    Optional<DocenteEntity> pegaDocenteEntity(Long id);

    ResponseAtualizaDocente atualizaDocente(long id, RequestAtualizaDocente dto, String token);

    void deleteDocente(Long id);


    List<ResponsePegaDocente> pegaDocentes(String token);
}
