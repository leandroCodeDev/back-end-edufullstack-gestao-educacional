package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.CursoService;
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
    public ResponseEntity<ResponseCurso> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCurso request) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.criarCurso(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseCurso> atualizaCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody RequestCurso dto
    ) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.atualizaCurso(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCurso> pegaCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaCurso(id));
    }
}
