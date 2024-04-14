package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.NotaService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService service;
    private final TokenService tokenService;

    public NotaController(ColecaoService colecao) {
        this.service = colecao.getNotaService();
        this.tokenService = colecao.getTokenService();
        this.service.setAlunoService(colecao.getAlunoService());
        this.service.setDocenteService(colecao.getDocenteService());
        this.service.setMateriaService(colecao.getMateriaService());
        this.service.setTokenService(colecao.getTokenService());

    }

    @PostMapping()
    public ResponseEntity<ResponseNota> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestNota request) {


        return ResponseEntity.status(201).body(service.criarNota(request, token));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseNota> atualizaNota(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid RequestNota dto
    ) {


        return ResponseEntity.ok(service.atualizaNota(id, dto, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNota> pegaNota(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {


        return ResponseEntity.ok(service.pegaNota(id, token));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        this.tokenService.validateAdmin(token);
        service.deleteNota(id);
        return ResponseEntity.status(204).build();
    }
}
