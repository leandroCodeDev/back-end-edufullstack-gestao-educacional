package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePontuacao;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@Slf4j
public class AlunoController {

    private final AlunoService service;
    private final TokenService tokenService;


    public AlunoController(ColecaoService colecaoService) {
        this.service = colecaoService.getAlunoService();
        this.tokenService = colecaoService.getTokenService();
        this.service.setTurmaService(colecaoService.getTurmaService());
        this.service.setUsuarioService(colecaoService.getUsuarioService());
        this.service.setTokenService(colecaoService.getTokenService());

    }


    @PostMapping
    public ResponseEntity<ResponseAluno> criarAluno(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody @Valid RequestCriaAluno dto
    ) {
        log.info("Chamou metodo post de criar aluno");
        return ResponseEntity.status(201).body(service.criarAluno(dto, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAluno> atualizaAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id,
            @RequestBody @Valid RequestAluno dto
    ) {
        log.info("Chamou metodo put de atualizar aluno");
        return ResponseEntity.ok(service.atualizaAluno(id, dto, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAluno> pegaAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo get de para pegar o aluno pelo id");
        return ResponseEntity.ok(service.pegaAluno(id, token));
    }


    @GetMapping("/{id}/notas")
    public ResponseEntity<List<ResponseNota>> pegaNotasAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo get de para pegar as notas de aluno pelo id");
        return ResponseEntity.ok(service.pegaNotasAluno(id, token));
    }

    @GetMapping("/{id}/pontuacao")
    public ResponseEntity<ResponsePontuacao> pegaPontuacaoAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo get de para pegar a pontuação de aluno pelo id");
        return ResponseEntity.ok(service.pegaPontuacaoAluno(id, token));
    }

    @GetMapping()
    public ResponseEntity<List<ResponseAluno>> pegaAluno(
            @RequestHeader(name = "Authorization") String token
    ) {
        log.info("Chamou metodo get de para pegar os alunos");
        return ResponseEntity.ok(service.pegaAlunos(token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletAluno(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable(name = "id") long id
    ) {
        log.info("Chamou metodo delete de para deletar o aluno pelo id");
        tokenService.validateAdmin(token);
        service.deleteAluno(id);
        return ResponseEntity.status(204).build();
    }

}
