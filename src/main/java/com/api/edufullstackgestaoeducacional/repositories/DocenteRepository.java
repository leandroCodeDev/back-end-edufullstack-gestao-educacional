package com.api.edufullstackgestaoeducacional.repositories;


import com.api.edufullstackgestaoeducacional.entities.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {

    Optional<DocenteEntity> findByUsuarioId(Long usuario);

}
