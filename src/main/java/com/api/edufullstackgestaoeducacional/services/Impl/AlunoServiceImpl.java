package com.api.edufullstackgestaoeducacional.services.Impl;


import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.requests.RequestCriaAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseAluno;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponseNota;
import com.api.edufullstackgestaoeducacional.controllers.dtos.responses.ResponsePontuacao;
import com.api.edufullstackgestaoeducacional.entities.AlunoEntity;
import com.api.edufullstackgestaoeducacional.entities.NotaEntity;
import com.api.edufullstackgestaoeducacional.entities.TurmaEntity;
import com.api.edufullstackgestaoeducacional.entities.UsuarioEntity;
import com.api.edufullstackgestaoeducacional.exception.erros.NotFoundException;
import com.api.edufullstackgestaoeducacional.exception.erros.NotValidException;
import com.api.edufullstackgestaoeducacional.exception.erros.UnauthorizedException;
import com.api.edufullstackgestaoeducacional.repositories.AlunoRepository;
import com.api.edufullstackgestaoeducacional.services.AlunoService;
import com.api.edufullstackgestaoeducacional.services.TokenService;
import com.api.edufullstackgestaoeducacional.services.TurmaService;
import com.api.edufullstackgestaoeducacional.services.UsuarioService;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    @Setter
    private UsuarioService usuarioService;

    @Setter
    private TurmaService turmaService;

    @Setter
    private TokenService tokenService;

    public AlunoServiceImpl(AlunoRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseAluno criarAluno(RequestCriaAluno dto, String token) {
        String perfil = tokenService.buscaCampo(token, "perfil");
        if (!perfil.equals("PEDAGOGICO") && !perfil.equals("ADMIN")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }

        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin(dto.login());
        TurmaEntity turma = turmaService.pegaTurmaEntity(dto.turmaId()).orElseThrow(() -> new NotFoundException("Turma não encontrado"));

        if (!dto.dataNascimento().before(new Date())) {
            throw new NotValidException("A validação Falhou", "A dataNascimento não pode ser uma data futura");
        }

        if (user == null) {
            throw new NotValidException("A validação Falhou", "login de usuario não existe");
        }
        exiteUsuario(user.getId());
        AlunoEntity aluno = new AlunoEntity(dto, user, turma);
        aluno = repository.save(aluno);
        aluno = repository.findById(aluno.getId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        return aluno.toResponseAluno();
    }

    @Override
    public ResponseAluno pegaAluno(Long id, String token) {
        String perfil = tokenService.buscaCampo(token, "perfil");
        if (!perfil.equals("PEDAGOGICO") && !perfil.equals("ADMIN")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
        AlunoEntity aluno = pegaAlunoEntity(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        return aluno.toResponseAluno();
    }

    @Override
    public Optional<AlunoEntity> pegaAlunoEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public ResponseAluno atualizaAluno(long id, RequestAluno dto, String token) {
        String perfil = tokenService.buscaCampo(token, "perfil");
        if (!perfil.equals("PEDAGOGICO") && !perfil.equals("ADMIN")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }

        AlunoEntity aluno = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        UsuarioEntity user = usuarioService.pegaUmUsuarioPeloLogin(dto.login());
        TurmaEntity turma = turmaService.pegaTurmaEntity(dto.turmaId()).orElseThrow(() -> new NotFoundException("Turma não encontrado"));

        if (user == null) {
            throw new NotValidException("A validação Falhou", "login de usuario não existe");
        }

        if (!dto.dataNascimento().before(new Date())) {
            throw new NotValidException("A validação Falhou", "A dataNascimento não pode ser uma data futura");
        }
        exiteUsuario(id, user.getId());

        if (!aluno.getNome().equals(dto.nome())) {
            aluno.setNome(dto.nome());
        }
        if (dto.dataNascimento().before(new Date())
                || dto.dataNascimento().equals(new Date())
        ) {
            aluno.setDataNascimento(dto.dataNascimento());
        }
        if (!aluno.getUsuario().getLogin().equals(dto.login())) {
            aluno.setUsuario(user);
        }

        if (aluno.getTurma().getId() != dto.turmaId()) {
            aluno.setTurma(turma);
        }

        aluno = repository.save(aluno);
        aluno = repository.findById(aluno.getId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        return aluno.toResponseAluno();
    }

    @Override
    public void deleteAluno(Long id) {
        AlunoEntity aluno = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        repository.delete(aluno);
    }

    @Override
    public List<ResponseAluno> pegaAlunos(String token) {
        String perfil = tokenService.buscaCampo(token, "perfil");
        if (!perfil.equals("PEDAGOGICO") && !perfil.equals("ADMIN")) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }
        List<AlunoEntity> alunos = repository.findAll();
        if (alunos.size() <= 0) {
            throw new NotFoundException("Não há alunos cadastrados.");
        }
        return alunos.stream()
                .map(AlunoEntity::toResponseAluno)
                .collect(Collectors.toList());

    }

    @Override
    public List<ResponseNota> pegaNotasAluno(long id, String token) {
        AlunoEntity aluno = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (
                (tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("ALUNO")
                        && !aluno.getUsuario().getLogin().equals(tokenService.buscaCampo(token, "scope")))
                        || (!tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("PROFESSOR")
                        && !tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("PEDAGOGICO")
                        && !tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("ADMIN"))
        ) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }

        List<NotaEntity> notas = aluno.getNotas();
        if (notas.size() <= 0) {
            throw new NotFoundException("Não há notas cadastradas para o aluno especificado.");
        }

        return notas.stream()
                .map(NotaEntity::toResponseNota)
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePontuacao pegaPontuacaoAluno(long id, String token) {
        AlunoEntity aluno = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        if (
                (tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("ALUNO")
                        && !aluno.getUsuario().getLogin().equals(tokenService.buscaCampo(token, "scope")))
                        || (!tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("PROFESSOR")
                        && !tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("PEDAGOGICO")
                        && !tokenService.buscaCampo(token, "perfil").equalsIgnoreCase("ADMIN"))
        ) {
            throw new UnauthorizedException("Acesso não autorizado", "Usuario não tem acesso a essa funcionalidade");
        }


        List<NotaEntity> notas = aluno.getNotas();
        if (notas.size() <= 0) {
            return new ResponsePontuacao(0.0);
        }

        double soma = notas.stream()
                .mapToDouble(NotaEntity::getValor) // Mapeie para os valores
                .reduce(0, Double::sum);

        double media = soma / notas.size();

        double pontuacao = media * 10;

        return new ResponsePontuacao(pontuacao);
    }

    private void exiteUsuario(Long id) {
        if (repository.findByUsuarioId(id).isPresent()) {
            throw new NotValidException("A validação falhou!", "Usuario ja vinculado a outro aluno!");
        }
    }

    private void exiteUsuario(Long idAluno, Long idUsuario) {
        Optional<AlunoEntity> aluno = repository.findByUsuarioId(idUsuario);
        if (aluno.isPresent() && aluno.get().getId() != idAluno) {
            throw new NotValidException("A validação falhou!", "Usuario ja vinculado a outro aluno!");
        }
    }
}
