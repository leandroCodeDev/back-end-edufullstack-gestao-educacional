package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class CadastroController {

    private final UsuarioService service;

    public CadastroController(ColecaoService colecao) {
        this.service = colecao.getUsuarioService();
        this.service.setPerfilService(colecao.getPerfilService());
        this.service.setTokenService(colecao.getTokenService());
    }

    @PostMapping()
    public ResponseEntity<ResponseNovoUsuario> cadastrar(@RequestBody @Valid RequestNovoUsuario request) {
        return ResponseEntity.ok(service.cadastrar(request));
    }
}
