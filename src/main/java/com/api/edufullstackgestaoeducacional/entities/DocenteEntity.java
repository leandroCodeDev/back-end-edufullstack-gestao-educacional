package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAtualizaDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseCriarDocente;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePegaDocente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;


@Data
@Entity
@Table(name = "docente")
@AllArgsConstructor
@NoArgsConstructor
public class DocenteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada", nullable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date dataEntrada;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", unique = true)
    private UsuarioEntity usuario;


    public DocenteEntity(RequestCriarDocente dto) {
        this.nome = dto.nome();
    }


    public DocenteEntity(RequestCriarDocente dto, UsuarioEntity user) {
        this.nome = dto.nome();
        this.usuario = user;
    }

    @PrePersist
    public void prePersist() {
        // Define a data atual se a data de empréstimo for nula
        if (dataEntrada == null) {
            dataEntrada = new Date();
        }
    }

    public ResponseCriarDocente toResponseCriarDocente() {
        return new ResponseCriarDocente(this.id, this.nome, this.dataEntrada, this.usuario.getId());
    }

    public ResponsePegaDocente toResponsePegaDocente() {
        return new ResponsePegaDocente(this.id, this.nome, this.usuario.getId());
    }

    public ResponseAtualizaDocente toResponseAtualizaDocente() {
        return new ResponseAtualizaDocente(this.id, this.nome, this.getDataEntrada(), this.usuario.getId());

    }
}
