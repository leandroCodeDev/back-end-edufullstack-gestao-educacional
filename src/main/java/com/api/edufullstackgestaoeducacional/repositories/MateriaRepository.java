package com.api.edufullstackgestaoeducacional.repositories;


import com.api.edufullstackgestaoeducacional.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {


}
