package com.api.edufullstackgestaoeducacional.controllers;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.services.ColecaoService;
import com.api.edufullstackgestaoeducacional.services.DocenteService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
