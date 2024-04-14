package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@Slf4j

public class CursoController {

    private final CursoService service;
    private final TokenService tokenService;

    public CursoController(ColecaoService colecao) {
        this.service = colecao.getCursoService();
        this.tokenService = colecao.getTokenService();
        this.service.setTokenService(colecao.getTokenService());


    }

    @PostMapping()
    public ResponseEntity<ResponseCurso> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCurso request) {

        log.info("Chamou metodo post para criar um curso");
        return ResponseEntity.status(201).body(service.criarCurso(request, token));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseCurso> atualizaCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestCurso dto
    ) {

        log.info("Chamou metodo put para atualizar um curso");
        return ResponseEntity.ok(service.atualizaCurso(id, dto, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCurso> pegaCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        log.info("Chamou metodo get para pegar um curso pelo id");
        return ResponseEntity.ok(service.pegaCurso(id, token));
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<ResponseMateria>> pegaMateriasCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        log.info("Chamou metodo get para pegar as materias de um curso");
        return ResponseEntity.ok(service.pegaMateriasdoCurso(id, token));
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseCurso>> pegaCursos(
            @RequestHeader(name = "Authorization") String token) {

        log.info("Chamou metodo get para pegar os cursos");

        return ResponseEntity.ok(service.pegaCursos(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        log.info("Chamou metodo delete para remover um curso");

        this.tokenService.validateAdmin(token);
        service.deleteCurso(id);
        return ResponseEntity.status(204).build();
    }
}
