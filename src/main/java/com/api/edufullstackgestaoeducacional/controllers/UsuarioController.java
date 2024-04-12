package com.api.edufullstackgestaoeducacional.controllers;


import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(ColecaoService colecao) {
        this.service = colecao.getUsuarioService();
        this.service.setPerfilService(colecao.getPerfilService());
        this.service.setTokenService(colecao.getTokenService());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> pegaUm(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok("achou");
    }
}
