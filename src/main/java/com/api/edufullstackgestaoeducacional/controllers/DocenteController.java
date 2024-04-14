package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAtualizaDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAtualizaDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaDocente;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.DocenteService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
@Slf4j
public class DocenteController {

    private final DocenteService service;
    private final TokenService tokenService;


    public DocenteController(ColecaoService colecaoService) {
        this.service = colecaoService.getDocenteService();
        this.tokenService = colecaoService.getTokenService();
        this.service.setTokenService(colecaoService.getTokenService());
        this.service.setUsuarioService(colecaoService.getUsuarioService());
    }


    @PostMapping
    public ResponseEntity<ResponseCriarDocente> criarDocente(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCriarDocente dto
    ) {
        log.info("Chamou metodo post para criar um docente");
        return ResponseEntity.status(201).body(service.criarDocente(dto, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAtualizaDocente> atualizaDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id,
            @RequestBody @Valid RequestAtualizaDocente dto
    ) {
        log.info("Chamou metodo put para atualizar um docente");
        return ResponseEntity.ok(service.atualizaDocente(id, dto, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePegaDocente> pegaDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo get para pegar um docente");
        return ResponseEntity.ok(service.pegaDocente(id, token));
    }


    @GetMapping()
    public ResponseEntity<List<ResponsePegaDocente>> pegaDocente(
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("Chamou metodo get para pegar todos docentes");
        return ResponseEntity.ok(service.pegaDocentes(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo delete para deleta um docente");
        tokenService.validateAdmin(token);
        service.deleteDocente(id);
        return ResponseEntity.status(204).build();
    }

}
