package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaDocente;

public interface DocenteService {


    void setTokenService(TokenService tokenService);

    void setUsuarioService(UsuarioService usuarioService);

    ResponseCriarDocente criarDocente(RequestCriarDocente dto);

    ResponsePegaDocente pegaDocente(Long id);

}
