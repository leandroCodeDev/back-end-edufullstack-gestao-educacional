package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.MateriaService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService service;
    private final TokenService tokenService;

    public MateriaController(ColecaoService colecao) {
        this.service = colecao.getMateriaService();
        this.tokenService = colecao.getTokenService();

    }

    @PostMapping()
    public ResponseEntity<ResponseMateria> cadastrar(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestMateria request) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.criarMateria(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseMateria> atualizaMateria(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id,
            @RequestBody RequestMateria dto
    ) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.atualizaMateria(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMateria> pegaMateria(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaMateria(id));
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseMateria>> pegaMaterias(
            @RequestHeader(name = "Authorization") String token) {

        this.tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaMaterias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") Long id) {

        this.tokenService.validateAdmin(token);
        service.deleteMateria(id);
        return ResponseEntity.status(204).build();
    }
}
