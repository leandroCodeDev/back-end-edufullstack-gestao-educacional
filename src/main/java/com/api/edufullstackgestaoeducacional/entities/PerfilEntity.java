package com.api.edufullstackgestaoeducacional.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "perfil")
@AllArgsConstructor
@NoArgsConstructor
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;


    public PerfilEntity(String nome) {
        log.info("cria nova entidade de materia");
        this.nome = nome;
    }

    public PerfilEntity(Long id) {
        log.info("cria nova entidade de perfil");
        this.id = id;
    }
}
