package com.api.edufullstackgestaoeducacional.repositories;


import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    Optional<AlunoEntity> findByUsuarioId(Long usuario);

}
