package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarCurso;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.ResponseCriarCurso;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;
    private final TokenService tokenService;

    public CursoController(ColecaoService colecao) {
        this.service = colecao.getCursoService();
        this.tokenService = colecao.getTokenService();

    }

    @PostMapping()
    public ResponseEntity<ResponseCriarCurso> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCriarCurso request) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.criarCurso(request));
    }
}
