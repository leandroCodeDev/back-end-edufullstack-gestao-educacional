package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;
    private final TokenService tokenService;


    public AlunoController(ColecaoService colecaoService) {
        this.service = colecaoService.getAlunoService();
        this.tokenService = colecaoService.getTokenService();
        this.service.setTurmaService(colecaoService.getTurmaService());
        this.service.setUsuarioService(colecaoService.getUsuarioService());
    }


    @PostMapping
    public ResponseEntity<ResponseAluno> criarAluno(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCriaAluno dto
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.status(201).body(service.criarAluno(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAluno> atualizaAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id,
            @RequestBody @Valid RequestAluno dto
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.atualizaAluno(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAluno> pegaAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaAluno(id));
    }


    @GetMapping("/{id}/notas")
    public ResponseEntity<List<ResponseNota>> pegaNotasAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaNotasAluno(id));
    }

    @GetMapping()
    public ResponseEntity<List<ResponseAluno>> pegaAluno(
            @RequestHeader(name = "Authorization") String token
    ) {
        tokenService.validateAdmin(token);
        return ResponseEntity.ok(service.pegaAlunos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        tokenService.validateAdmin(token);
        service.deleteAluno(id);
        return ResponseEntity.status(204).build();
    }

}
