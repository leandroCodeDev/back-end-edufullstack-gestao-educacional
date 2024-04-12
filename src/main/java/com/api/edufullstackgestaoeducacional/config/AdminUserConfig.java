package com.api.edufullstackgestaoeducacional.config;

import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestNovoUsuario;
import com.api.edufullstackgestaoeducacional.entities.PerfilEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.services.PerfilService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {

    private PerfilService perfilService;
    private UsuarioService usuarioService;


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        PerfilEntity perfil = perfilService.pegaUm("ADMIN");
        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin("admin");
        if (user == null) {
            usuarioService.cadastrar(new RequestNovoUsuario("admin", "admin", perfil.getId()));
        } else {
            System.out.println("admin ja cadastrado");
        }
    }
}
