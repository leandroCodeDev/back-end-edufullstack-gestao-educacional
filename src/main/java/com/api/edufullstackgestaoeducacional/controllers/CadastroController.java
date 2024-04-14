package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastrar")
@Slf4j

public class CadastroController {

    private final UsuarioService service;
    private final TokenService tokenService;

    public CadastroController(ColecaoService colecao) {
        this.service = colecao.getUsuarioService();
        this.service.setPerfilService(colecao.getPerfilService());
        this.tokenService = colecao.getTokenService();
        this.service.setTokenService(colecao.getTokenService());
    }

    @PostMapping()
    public ResponseEntity<ResponseNovoUsuario> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestNovoUsuario request) {
        log.info("Chamou metodo post para realizar o cadastro de usuario");
        this.tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.cadastrar(request));
    }
}
