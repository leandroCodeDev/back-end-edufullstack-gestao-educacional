package com.api.edufullstackgestaoeducacional.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;


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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docente_id")
    private DocenteEntity docente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_id")
    private MateriaEntity materia;

}
