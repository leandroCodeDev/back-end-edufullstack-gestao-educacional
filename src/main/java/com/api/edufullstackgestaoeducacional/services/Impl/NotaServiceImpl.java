package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;
import com.api.edufullstackgestaoeducacional.entities.DocenteEntity;
import com.api.edufullstackgestaoeducacional.entities.MateriaEntity;
import com.api.edufullstackgestaoeducacional.entities.NotaEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.exception.erros.UnauthorizedException;
import com.api.edufullstackgestaoeducacional.repositories.NotaRepository;
import com.api.edufullstackgestaoeducacional.services.*;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;

    @Setter
    private AlunoService alunoService;

    @Setter
    private DocenteService docenteService;

    @Setter
    private MateriaService materiaService;

    @Setter
    private TokenService tokenService;


    public NotaServiceImpl(NotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseNota criarNota(RequestNota dto, String token) {
        validaAcesso(token);
        DocenteEntity docente = docenteService.pegaDocenteEntity(dto.docenteId()).orElseThrow(() -> new NotFoundException("Docente não encontrado"));
        if (!docente.getUsuario().getPerfil().getNome().equals("PROFESSOR")) {
            throw new NotFoundException("O Docente não tem papel de Professor");
        }
        MateriaEntity materia = materiaService.pegaMateriaEntity(dto.materiaId()).orElseThrow(() -> new NotFoundException("Materia não encontrado"));

        AlunoEntity aluno = alunoService.pegaAlunoEntity(dto.alunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (aluno.getTurma().getCurso().getId() != materia.getCurso().getId()) {
            throw new NotFoundException("Aluno não matriculado ao curso dessa materia");
        }

        NotaEntity nota = new NotaEntity(dto, docente, materia, aluno);
        nota = repository.save(nota);
        nota = pegaNotaEntity(nota.getId()).orElseThrow(() -> new NotFoundException("Nota não encontrado"));

        return nota.toResponseNota();
    }

    @Override
    public ResponseNota pegaNota(Long id, String token) {
        validaAcesso(token);
        NotaEntity nota = pegaNotaEntity(id).orElseThrow(() -> new NotFoundException("Nota não encontrado"));
        return nota.toResponseNota();
    }

    @Override
    public Optional<NotaEntity> pegaNotaEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseNota atualizaNota(long id, RequestNota dto, String token) {
        validaAcesso(token);
        NotaEntity nota = pegaNotaEntity(id).orElseThrow(() -> new NotFoundException("Nota não encontrado"));
        DocenteEntity docente = docenteService.pegaDocenteEntity(dto.docenteId()).orElseThrow(() -> new NotFoundException("Docente não encontrado"));
        if (!docente.getUsuario().getPerfil().getNome().equals("PROFESSOR")) {
            throw new NotFoundException("O Docente não tem papel de Professor");
        }
        MateriaEntity materia = materiaService.pegaMateriaEntity(dto.materiaId()).orElseThrow(() -> new NotFoundException("Materia não encontrado"));

        AlunoEntity aluno = alunoService.pegaAlunoEntity(dto.alunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (aluno.getTurma().getCurso().getId() != materia.getCurso().getId()) {
            throw new NotFoundException("Aluno não matriculado ao curso dessa materia");
        }


        if (nota.getValor() != dto.valor()) {
            nota.setValor(dto.valor());
        }

        if (materia.getId() != nota.getMateria().getId()) {
            nota.setMateria(materia);
        }

        if (aluno.getId() != nota.getAluno().getId()) {
            nota.setAluno(aluno);
        }
        if (docente.getId() != nota.getDocente().getId()) {
            nota.setDocente(docente);
        }
        nota = repository.save(nota);
        nota = pegaNotaEntity(nota.getId()).orElseThrow(() -> new NotFoundException("Nota não encontrado"));

        return nota.toResponseNota();
    }

    @Override
    public void deleteNota(Long id) {
        NotaEntity nota = pegaNotaEntity(id).orElseThrow(() -> new NotFoundException("Nota não encontrado"));
        repository.delete(nota);
    }

    private void validaAcesso(String token) {
        if (
                (!tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("PROFESSOR")
                        && !tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("ADMIN"))
        ) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
    }
}
