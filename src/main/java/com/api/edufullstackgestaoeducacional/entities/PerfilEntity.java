package com.api.edufullstackgestaoeducacional.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "perfil")
@AllArgsConstructor
@NoArgsConstructor
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;


    public PerfilEntity(String nome) {
        this.nome = nome;
    }
}
