package com.api.edufullstackgestaoeducacional.config;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private PerfilService perfilService;
    private UsuarioService usuarioService;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("roda comando de incialização de admin");
        PerfilEntity perfil = perfilService.pegaUm("ADMIN");
        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin("admin");
        if (user == null) {
            log.info("cria admin");
            usuarioService.cadastrar(new RequestNovoUsuario("admin", "admin", perfil.getId()));
        } else {
            log.info("admin ja cadastrado");
            System.out.println("admin ja cadastrado");
        }
    }
}
