package com.api.edufullstackgestaoeducacional.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "turma")
@AllArgsConstructor
@NoArgsConstructor
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docente_id")
    private DocenteEntity docente;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;
}
