package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCurso;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCurso;
import com.api.edufullstackgestaoeducacional.entities.CursoEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.repositories.CursoRepository;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;


    @Override
    public ResponseCurso criarCurso(RequestCurso dto) {
        CursoEntity curso = repository.save(new CursoEntity(dto));
        curso = repository.findById(curso.getId()).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        return curso.toResponseCurso();
    }

    @Override
    public ResponseCurso pegaCurso(Long id) {
        CursoEntity curso = pegaCursoEntity(id).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        return curso.toResponseCurso();
    }

    @Override
    public Optional<CursoEntity> pegaCursoEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseCurso atualizaCurso(long id, RequestCurso dto) {
        CursoEntity curso = repository.findById(id).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        if (!curso.getNome().equals(dto.nome())) {
            curso.setNome(dto.nome());
        }
        repository.save(curso);
        return curso.toResponseCurso();
    }

    @Override
    public void deleteCurso(Long id) {
        CursoEntity curso = repository.findById(id).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        repository.delete(curso);
    }

    @Override
    public List<ResponseCurso> pegaCursos() {
        List<CursoEntity> cursos = repository.findAll();
        if (cursos.size() <= 0) {
            throw new NotFoundException("Não há Cursos cadastrados.");
        }
        return cursos.stream().map(CursoEntity::toResponseCurso)
                .collect(Collectors.toList());
    }
}
