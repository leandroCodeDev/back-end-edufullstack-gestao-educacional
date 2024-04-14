package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.UnauthorizedException;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private static final long TEMPO_EXPIRACAO = 36000L; //contante de tempo de expiração em segundos
    private final JwtEncoder jwtEncoder; // codificar um JWT
    private final JwtDecoder jwtDencoder; // decofica um JWT

    public String gerarToken(
            UsuarioEntity user
    ) {

        Instant now = Instant.now();

        String scope = user.getLogin();

        JwtClaimsSet claims = JwtClaimsSet.builder() // Conjunto de campos do JWT, incluindo os campos pré-definidos e campos customizados
                .issuer("projeto") // autor do token
                .issuedAt(now)      // momento da criação do token
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO)) // tempo de expiração
                .subject(user.getId().toString())   // sujeito do token ou dono do token
                .claim("scope", scope) // campo customizado, chamado scope que será adicionado ao token, alem dos campos anteriores
                .claim("id", user.getId()) // campo customizado, chamado scope que será adicionado ao token, alem dos campos anteriores
                .claim("perfil", user.getPerfil().getNome())
                .build(); // constroi o Objeto de JwtClaimsSet

        return jwtEncoder.encode(
                        JwtEncoderParameters.from(claims) // parametros para encode do token
                ) // token foi criado, porém está em uma classe que não tem o token puro, ele o token e várias coisas a mais
                .getTokenValue(); // corpo de resposta é um objeto de LoginResponse
    }


    public String buscaCampo(String token, String claim) {
        return jwtDencoder
                .decode(token) // decifra o token
                .getClaims() // busca um campo especifico do token
                .get(claim)    // definindo o campo a ser buscado
                .toString(); // transforma a resposta em string
    }


    @Override
    public void validateAdmin(String token) {
        String perfil = buscaCampo(token.substring(7), "perfil");
        if (!perfil.equals("ADMIN")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }

    }

    @Override
    public void validatePedadogico(String token) {
        String perfil = buscaCampo(token.substring(7), "perfil");
        if (!perfil.equals("PEDAGOGICO")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
    }

    @Override
    public void validateRecruiter(String token) {
        String perfil = buscaCampo(token.substring(7), "perfil");
        if (!perfil.equals("RECRUITER")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
    }

    @Override
    public void validateProfessor(String token) {
        String perfil = buscaCampo(token.substring(7), "perfil");
        if (!perfil.equals("PROFESSOR")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
    }

    @Override
    public void validateAluno(String token) {
        String perfil = buscaCampo(token.substring(7), "perfil");
        if (!perfil.equals("ALUNO")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
    }
}
