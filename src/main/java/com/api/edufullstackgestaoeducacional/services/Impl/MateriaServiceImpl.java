package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import com.api.edufullstackgestaoeducacional.repositories.MateriaRepository;
import com.api.edufullstackgestaoeducacional.services.CursoService;
import com.api.edufullstackgestaoeducacional.services.MateriaService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository repository;

    @Setter
    private CursoService cursoService;

    public MateriaServiceImpl(MateriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseMateria criarMateria(RequestMateria dto) {
        return null;
    }

    @Override
    public ResponseMateria pegaMateria(Long id) {
        return null;
    }

    @Override
    public ResponseMateria atualizaMateria(long id, RequestMateria dto) {
        return null;
    }

    @Override
    public void deleteMateria(Long id) {

    }

    @Override
    public List<ResponseMateria> pegaMaterias() {
        return List.of();
    }
}
