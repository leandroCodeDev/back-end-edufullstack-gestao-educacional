package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.repositories.PerfilRepository;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository repository;

    @Override
    public List<PerfilEntity> pegaTodos() {

        return repository.findAll();
    }

    @Override
    public PerfilEntity pegaUm(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Elemento não encontrado"));
    }

    @Override
    public PerfilEntity pegaUm(String nome) {
        return repository.findByNome(nome).orElseThrow(() -> new RuntimeException("Elemento não encontrado"));
    }

    @Override
    public boolean existe(Long id) {
        return repository.findById(id).isPresent();

    }

    @Override
    public boolean existe(String nome) {
        return repository.findByNome(nome).isPresent();
    }
}
