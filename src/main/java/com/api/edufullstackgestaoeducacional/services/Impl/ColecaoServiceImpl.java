package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.services.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class ColecaoServiceImpl implements ColecaoService {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final TokenService tokenService;
    private final SenhaService senhaService;
    private final DocenteService docenteService;
    private final CursoService cursoService;
    private final MateriaService materiaService;
    private final TurmaService turmaService;
    private final AlunoService alunoService;
    private final NotaService notaService;


}
