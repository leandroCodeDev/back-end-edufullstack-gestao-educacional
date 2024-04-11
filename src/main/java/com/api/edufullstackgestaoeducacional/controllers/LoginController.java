package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseLogin;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService service;

    public LoginController(ColecaoService colecao) {
        this.service = colecao.getUsuarioService();
        this.service.setPerfilService(colecao.getPerfilService());
    }

    @PostMapping()
    public ResponseEntity<ResponseLogin> logar(@RequestBody @Valid RequestLogin request) {
        return ResponseEntity.ok(service.logar(request));
    }
}
