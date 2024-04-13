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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
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
        tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.criarDocente(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAtualizaDocente> atualizaDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id,
            @RequestBody @Valid RequestAtualizaDocente dto
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.atualizaDocente(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePegaDocente> pegaDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaDocente(id));
    }


    @GetMapping()
    public ResponseEntity<List<ResponsePegaDocente>> pegaDocente(
            @RequestHeader(name = "Authorization") String token
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaDocentes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletDocente(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        tokenService.validateAdmin(token);
        service.deleteDocente(id);
        return ResponseEntity.status(204).build();
    }

}
