package com.api.edufullstackgestaoeducacional.controllers.dtos.responses;


import java.util.Date;

public record ResponseCriarDocente(Long id, String nome, Date dataEntrada, Long usuarioId) {
}
