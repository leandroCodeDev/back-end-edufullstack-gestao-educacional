package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@Entity
@Table(name = "materia")
@AllArgsConstructor
@NoArgsConstructor
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @OneToMany(mappedBy = "materia", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<NotaEntity> notas;

    public MateriaEntity(RequestMateria dto, CursoEntity curso) {
        log.info("cria nova entidade de materia");
        this.nome = dto.nome();
        this.curso = curso;
    }

    public MateriaEntity(String nome) {
        log.info("cria nova entidade de materia");
        this.nome = nome;
    }

    public ResponseMateria toResponseMateria() {

        log.info("cria novo DTO de materia");
        return new ResponseMateria(this.id, this.nome);
    }
}
