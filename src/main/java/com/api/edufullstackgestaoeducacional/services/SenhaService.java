package com.api.edufullstackgestaoeducacional.services;

public interface SenhaService {

    String encriptSenha(String senha);

    boolean comparaSenha(String senha, String senhaEncriptada);
}
