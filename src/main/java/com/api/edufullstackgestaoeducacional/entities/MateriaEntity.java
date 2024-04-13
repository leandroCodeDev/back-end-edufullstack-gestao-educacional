package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestMateria;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseMateria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public MateriaEntity(RequestMateria dto, CursoEntity curso) {
        this.nome = dto.nome();
        this.curso = curso;
    }

    public MateriaEntity(String nome) {
        this.nome = nome;
    }

    public ResponseMateria toResponseMateria() {
        return new ResponseMateria(this.id, this.nome);
    }
}
