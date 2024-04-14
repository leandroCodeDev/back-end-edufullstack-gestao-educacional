package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestTurma;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseTurma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public TurmaEntity(RequestTurma dto, CursoEntity curso, DocenteEntity docente) {
        log.info("cria nova entidade de turma");
        this.nome = dto.nome();
        this.curso = curso;
        this.docente = docente;
    }

    public ResponseTurma toResponseTurma() {
        log.info("cria novo DTO de turma");
        return new ResponseTurma(id, nome);
    }
}
