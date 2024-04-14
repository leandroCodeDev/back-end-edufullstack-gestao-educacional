package com.api.edufullstackgestaoeducacional.services;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePontuacao;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;

import java.util.List;
import java.util.Optional;

public interface AlunoService {

    void setUsuarioService(UsuarioService usuarioService);

    void setTurmaService(TurmaService turmaService);

    void setTokenService(TokenService tokenService);

    ResponseAluno criarAluno(RequestCriaAluno dto, String token);

    ResponseAluno pegaAluno(Long id, String token);

    Optional<AlunoEntity> pegaAlunoEntity(Long id);

    ResponseAluno atualizaAluno(long id, RequestAluno dto, String token);

    void deleteAluno(Long id);

    List<ResponseAluno> pegaAlunos(String token);

    List<ResponseNota> pegaNotasAluno(long id, String token);

    ResponsePontuacao pegaPontuacaoAluno(long id, String token);
}
