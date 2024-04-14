package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.entities.CursoEntity;
import com.api.edufullstackgestaoeducacional.entities.MateriaEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.repositories.MateriaRepository;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.MateriaService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository repository;

    @Setter
    private CursoService cursoService;
    @Setter
    private TokenService tokenService;
    public MateriaServiceImpl(MateriaRepository repository) {
        log.info("cria service de materia");
        this.repository = repository;
    }

    @Override
    public ResponseMateria criarMateria(RequestMateria dto) {
        log.info("cria materia");
        CursoEntity curso = cursoService.pegaCursoEntity(dto.cursoId())
                .orElseThrow(
                        () -> new NotFoundException("Curso não encontrado")
                );


        MateriaEntity materia = new MateriaEntity(dto, curso);
        repository.save(materia);
        materia = repository.findById(materia.getId()).orElseThrow(() -> new NotFoundException("Materia não encontrado"));
        return materia.toResponseMateria();
    }

    @Override
    public ResponseMateria pegaMateria(Long id) {
        log.info("pega materia");
        MateriaEntity materia = pegaMateriaEntity(id).orElseThrow(() -> new NotFoundException("Materia não encontrado"));
        return materia.toResponseMateria();
    }

    @Override
    public Optional<MateriaEntity> pegaMateriaEntity(Long id) {
        log.info("cria entity de materia");
        return repository.findById(id);
    }


    @Override
    public ResponseMateria atualizaMateria(long id, RequestMateria dto) {
        log.info("atualiza materia");
        MateriaEntity materia = pegaMateriaEntity(id).orElseThrow(() -> new NotFoundException("Materia não encontrado"));
        CursoEntity curso = cursoService.pegaCursoEntity(dto.cursoId()).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        if (!materia.getNome().equals(dto.nome())) {
            materia.setNome(dto.nome());
        }
        if (materia.getCurso().getId() != curso.getId()) {
            materia.setCurso(curso);
        }
        repository.save(materia);
        materia = repository.findById(materia.getId()).orElseThrow(() -> new NotFoundException("Materia não encontrado"));
        return materia.toResponseMateria();
    }

    @Override
    public void deleteMateria(Long id) {
        log.info("deleta materia");
        MateriaEntity materia = repository.findById(id).orElseThrow(() -> new NotFoundException("Materia não encontrado"));
        repository.delete(materia);
    }

}
