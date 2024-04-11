package com.api.edufullstackgestaoeducacional.services;

import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;

import java.util.List;

public interface PerfilService {

    List<PerfilEntity> pegaTodos();

    PerfilEntity pegaUm(Long id);

    PerfilEntity pegaUm(String perfil);

    boolean existe(Long id);

    boolean existe(String perfil);

}
