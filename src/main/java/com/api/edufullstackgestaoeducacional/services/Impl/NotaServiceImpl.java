package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.entities.NotaEntity;
import com.api.edufullstackgestaoeducacional.repositories.NotaRepository;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.DocenteService;
import com.api.edufullstackgestaoeducacional.services.MateriaService;
import com.api.edufullstackgestaoeducacional.services.NotaService;
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

    public NotaServiceImpl(NotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseNota criarNota(RequestNota dto) {
        return null;
    }

    @Override
    public ResponseNota pegaNota(Long id) {
        return null;
    }

    @Override
    public Optional<NotaEntity> pegaNotaEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseNota atualizaNota(long id, RequestNota dto) {
        return null;
    }

    @Override
    public void deleteNota(Long id) {

    }
}
