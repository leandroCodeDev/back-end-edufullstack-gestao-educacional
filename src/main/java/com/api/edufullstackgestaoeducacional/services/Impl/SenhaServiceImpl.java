package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.services.SenhaService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Setter
@Service
@AllArgsConstructor
public class SenhaServiceImpl implements SenhaService {

    private BCryptPasswordEncoder bCryptEncoder;


    @Override
    public String encriptSenha(String senha) {
        return bCryptEncoder.encode(senha);
    }

    @Override
    public boolean comparaSenha(String senha, String senhaEncriptada) {
        return bCryptEncoder.matches(
                senha,
                senhaEncriptada
        );
    }
}
