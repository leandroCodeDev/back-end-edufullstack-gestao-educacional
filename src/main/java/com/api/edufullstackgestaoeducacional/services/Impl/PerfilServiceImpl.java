package com.api.edufullstackgestaoeducacional.services.Impl;

import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.repositories.PerfilRepository;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info("pega um perfil");
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Elemento não encontrado"));
    }

    @Override
    public PerfilEntity pegaUm(String nome) {
        log.info("pega um perfil");
        return repository.findByNome(nome).orElseThrow(() -> new RuntimeException("Elemento não encontrado"));
    }

    @Override
    public boolean existe(Long id) {
        log.info("valida se perfil existe");
        return repository.findById(id).isPresent();

    }

    @Override
    public boolean existe(String nome) {
        log.info("valida se perfil existe");
        return repository.findByNome(nome).isPresent();
    }


    public void validatePerfil(Long id) {
        log.info("valida perfil");
        if (!existe(id)) {
            throw new NotValidException("A validação falhou!", "O perfil informado não existe");
        }
    }

    public void validatePerfil(String nome) {
        log.info("valida perfil");
        if (!existe(nome)) {
            throw new NotValidException("A validação falhou!", "O perfil informado não existe");
        }
    }
}
