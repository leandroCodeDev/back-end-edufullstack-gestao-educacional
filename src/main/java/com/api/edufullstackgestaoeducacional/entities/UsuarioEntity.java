package com.api.edufullstackgestaoeducacional.entities;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestLogin;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNovoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String senha;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private PerfilEntity perfil;


    public UsuarioEntity(RequestLogin dto) {
        log.info("cria nova entidade de usuario");
        this.login = dto.login();
        this.senha = dto.senha();
    }

    public UsuarioEntity(RequestNovoUsuario dto) {
        log.info("cria nova entidade de usuario");
        this.login = dto.login();
        this.senha = dto.senha();
        this.perfil = new PerfilEntity(dto.perfilId());
    }


    public ResponseNovoUsuario toResponseNovoUsuarioDto() {
        log.info("cria novo DTO de usuario");
        return new ResponseNovoUsuario(this.id, this.login, this.perfil.getNome());
    }

}
