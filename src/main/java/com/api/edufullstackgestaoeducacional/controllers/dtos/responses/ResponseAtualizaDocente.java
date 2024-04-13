package com.api.edufullstackgestaoeducacional.controllers.dtos.responses;

import java.util.Date;

public record ResponseAtualizaDocente(Long id, String nome, Date dataEntrada, Long usuarioId) {
}
