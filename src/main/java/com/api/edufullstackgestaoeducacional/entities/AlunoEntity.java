package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@Entity
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date dataNascimento;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", unique = true)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turma_id", unique = true)
    private TurmaEntity turma;

    public AlunoEntity(RequestCriaAluno dto, UsuarioEntity user, TurmaEntity turma) {
        this.nome = dto.nome();
        this.dataNascimento = dto.dataNascimento();
        this.usuario = user;
        this.turma = turma;
    }

    public ResponseAluno toResponseAluno() {
        return new ResponseAluno(this.id, this.nome, this.dataNascimento);
    }
}
