package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Slf4j
@Data
@Entity
@Table(name = "nota")
@AllArgsConstructor
@NoArgsConstructor
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id", unique = false)
    private AlunoEntity aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docente_id", unique = false)
    private DocenteEntity docente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_id", unique = false)
    private MateriaEntity materia;

    public NotaEntity(RequestNota dto, DocenteEntity docente, MateriaEntity materia, AlunoEntity aluno) {
        this.valor = dto.valor();
        this.docente = docente;
        this.materia = materia;
        this.aluno = aluno;
    }

    @PrePersist
    public void prePersist() {
        log.info("popular data antes de inserir o registro na tabela");
        // Define a data atual se a data de empréstimo for nula
        if (data == null) {
            data = new Date();
        }
    }

    public ResponseNota toResponseNota() {
        log.info("cria novo DTO de nota");
        return new ResponseNota(id, valor, data);
    }
}
