package com.api.edufullstackgestaoeducacional.repositories;


import com.api.edufullstackgestaoeducacional.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

}
