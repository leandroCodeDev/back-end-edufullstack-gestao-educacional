package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarCurso;
import com.api.edufullstackgestaoeducacional.services.ResponseCriarCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "curso")
@AllArgsConstructor
@NoArgsConstructor
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;


    public CursoEntity(RequestCriarCurso dto) {
        this.nome = dto.nome();
    }

    public ResponseCriarCurso toResponseCriarCurso() {
        return new ResponseCriarCurso(this.id, this.nome);
    }
}
