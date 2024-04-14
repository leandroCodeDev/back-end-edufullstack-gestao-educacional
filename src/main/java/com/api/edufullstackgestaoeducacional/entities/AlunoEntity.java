package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "aluno")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
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

    @OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
    private List<NotaEntity> notas;



    public AlunoEntity(RequestCriaAluno dto, UsuarioEntity user, TurmaEntity turma) {
        this.nome = dto.nome();
        this.dataNascimento = dto.dataNascimento();
        this.usuario = user;
        this.turma = turma;
        log.info("cria nova entidade de aluno");
    }

    public ResponseAluno toResponseAluno() {
        log.info("cria novo DTO  de aluno");
        return new ResponseAluno(this.id, this.nome, this.dataNascimento);
    }
}
