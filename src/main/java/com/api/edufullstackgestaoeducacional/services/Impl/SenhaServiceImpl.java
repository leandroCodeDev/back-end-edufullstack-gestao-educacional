package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.services.SenhaService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Setter
@Service
@AllArgsConstructor
public class SenhaServiceImpl implements SenhaService {

    private BCryptPasswordEncoder bCryptEncoder;


    @Override
    public String encriptSenha(String senha) {
        log.info("encripta senha");
        return bCryptEncoder.encode(senha);
    }

    @Override
    public boolean compararSenha(String senha, String senhaEncriptada) {
        log.info("compara senhas");
        return bCryptEncoder.matches(
                senha,
                senhaEncriptada
        );
    }
}
