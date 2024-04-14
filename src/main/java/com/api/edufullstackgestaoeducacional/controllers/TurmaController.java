package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestTurma;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseTurma;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService service;
    private final TokenService tokenService;

    public TurmaController(ColecaoService colecao) {
        this.tokenService = colecao.getTokenService();
        this.service = colecao.getTurmaService();
        this.service.setCursoService(colecao.getCursoService());
        this.service.setDocenteService(colecao.getDocenteService());
        this.service.setTokenService(colecao.getTokenService());

    }

    @PostMapping()
    public ResponseEntity<ResponseTurma> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestTurma request) {


        return ResponseEntity.status(201).body(service.criarTurma(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseTurma> atualizaTurma(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestTurma dto
    ) {


        return ResponseEntity.ok(service.atualizaTurma(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTurma> pegaTurma(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {


        return ResponseEntity.ok(service.pegaTurma(id));
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseTurma>> pegaTurmas(
            @RequestHeader(name = "Authorization") String token) {


        return ResponseEntity.ok(service.pegaTurmas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        this.tokenService.validateAdmin(token);
        service.deleteTurma(id);
        return ResponseEntity.status(204).build();
    }
}
