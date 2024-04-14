package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;

import java.util.List;
import java.util.Optional;

public interface AlunoService {

    void setUsuarioService(UsuarioService usuarioService);

    void setTurmaService(TurmaService turmaService);

    ResponseAluno criarAluno(RequestCriaAluno dto);

    ResponseAluno pegaAluno(Long id);

    Optional<AlunoEntity> pegaAlunoEntity(Long id);

    ResponseAluno atualizaAluno(long id, RequestAluno dto);

    void deleteAluno(Long id);

    List<ResponseAluno> pegaAlunos();

    List<ResponseNota> pegaNotasAluno(long id);
}
