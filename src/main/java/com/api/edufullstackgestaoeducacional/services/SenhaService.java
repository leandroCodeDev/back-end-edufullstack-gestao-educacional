package com.api.edufullstackgestaoeducacional.services;

public interface SenhaService {

    String encriptSenha(String senha);

    boolean compararSenha(String senha, String senhaEncriptada);
}
