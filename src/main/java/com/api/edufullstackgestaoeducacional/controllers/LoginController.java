package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    private final UsuarioService service;

    public LoginController(ColecaoService colecao) {
        this.service = colecao.getUsuarioService();
        this.service.setPerfilService(colecao.getPerfilService());
        this.service.setTokenService(colecao.getTokenService());
    }

    @PostMapping()
    public ResponseEntity<ResponseLogin> logar(@RequestBody @Valid RequestLogin request) {
        log.info("Chamou metodo post para logar um usuario");
        return ResponseEntity.ok(service.logar(request));
    }
}
