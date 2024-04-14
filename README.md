# Educação FullStack - Sistema de Gestão Educacional

## introdução

O Educação FullStack é um sistema web de gestão educacional desenvolvido como parte do projeto avaliativo 1 do módulo 1.
Este sistema proporciona funcionalidades essenciais para a administração de instituições educacionais, permitindo o
cadastro de turmas, conteúdos, docentes e alunos, além de oferecer recursos para alocação de professores, gerenciamento
de notas e controle de acesso dos usuários. Desenvolvido utilizando Java, Spring e API REST, o Educação FullStack
emprega tecnologias modernas e práticas de desenvolvimento, incluindo Spring Security para controle de segurança,
PostgreSQL para armazenamento de dados e GitHub para versionamento do código. Além disso, a metodologia de
desenvolvimento utiliza o Kanban no Trello para organização das tarefas, garantindo uma abordagem ágil e eficiente. O
sistema atende a uma variedade de papéis de usuários, como administradores, docentes e alunos, cada um com suas
permissões específicas de acesso e funcionalidades disponíveis. Com uma arquitetura sólida e uma implementação cuidadosa
das regras de negócio, o Educação FullStack é uma solução completa para a gestão educacional, proporcionando uma
experiência integrada e eficaz para todas as partes envolvidas no processo educativo.

## Tecnologias Utilizadas

- [Java](https://www.java.com/pt-BR/download/help/whatis_java.html)
- [Spring](https://spring.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)

## Ferramentas Utilizadas

- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)
- [Insomnia](https://insomnia.rest/)
- [Trello](https://trello.com/pt-BR)

## Pré-Requisitos

- JDK 21 ou superior instalado
- Git
- IntelliJ IDEA
- Docker
- Docker Compose

## Dependências

- **Spring Boot Starter for Spring Data JPA**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-data-jpa`

- **Spring Boot Starter for Spring Web**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-web`

- **Spring Boot DevTools for hot reloading**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-devtools`
    - Scope: `runtime`
    - Optional: `true`

- **PostgreSQL Driver**:
    - GroupId: `org.postgresql`
    - ArtifactId: `postgresql`
    - Scope: `runtime`

- **Lombok**:
    - GroupId: `org.projectlombok`
    - ArtifactId: `lombok`
    - Optional: `true`

- **Spring Boot Starter for Testing**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-test`
    - Scope: `test`

- **Spring Boot Starter for Validation**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-validation`

- **Spring Boot Starter for OAuth2 Resource Server**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-oauth2-resource-server`

- **Spring Boot Starter for Security**:
    - GroupId: `org.springframework.boot`
    - ArtifactId: `spring-boot-starter-security`

- **Spring Security Test**:
    - GroupId: `org.springframework.security`
    - ArtifactId: `spring-security-test`
    - Scope: `test`

## Como Começar

### Instalação

**Clonando o Repositório**

```bash
git clone https://github.com/leandroCodeDev/edufullstack-gestao-educacional
```

**Inicializando o Banco de Dados**

```bash
docker-compose up -d
```

**Importando arquivo com as rotas da API para o Insomnia**

Se você usa o Insomnia para testar APIs, pode importar as requisições deste projeto diretamente para o Insomnia. Para
fazer isso, siga estas etapas:

1. Baixe o arquivo do Insomnia clicando em um link abaixo.
    - [Insomnia.har](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/colecoesRotas/Insomnia.har)
    - [InsomniaV4.json](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/colecoesRotas/InsomniaV4.json)
    - [InsomniaV4.yaml](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/colecoesRotas/InsomniaV4.yaml)

2. Abra o Insomnia e clique em "create" no canto superior direito .

3. Selecione "Import" e escolha a opção "From File".

4. Navegue até onde o arquivo do Insomnia foi baixado e selecione-o.

5. As requisições serão importadas para o seu ambiente do Insomnia.

Agora você pode usar as requisições diretamente no Insomnia para testar a API deste projeto.

**Precauções**

Tenha as portas 8092 e 5434 liberadas; caso contrário, altere a configuração do docker-compose.yml e as configurações do Spring, como porta de execução e porta de conexão do banco de dados, se necessário.

Porta 8092 ocupada altera a propriedade abaixo o arquivo [application.properties](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/src/main/resources/application.properties)

```bash
server.port={ PORTA }
```

Porta 5434 ocupada altera a propriedade abaixo o arquivo [application.properties](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/src/main/resources/application.properties)

```bash
spring.datasource.url=jdbc:postgresql://localhost:{ PORTA }/gestao-educacional
```
E a propriedade abaixo no aquivo [docker-compose.yml](https://github.com/leandroCodeDev/edufullstack-gestao-educacional/blob/main/docker-compose.yml)

```bash
    ports:
      - { PORTA }:5432
```


**Execução**

Abra o projeto no IntelliJ IDEA e execute-o usando o atalho de execução da IDE.

Abra o seu navegador ou sua plataforma de api e insira o link [localhost:8092](localhost:8092)

## Entidades do Projeto

O projeto possui as seguintes entidades, cada uma representando um elemento essencial do sistema:

### Entidade Papel:

- **id** (Long): Identificador único do papel.
- **nome** (String): Nome do papel.

### Entidade Usuário:

- **id** (Long): Identificador único do usuário.
- **login** (String): Nome de usuário para login.
- **senha** (String): Senha do usuário para autenticação.
- **id_papel** (Papel): Papel ou função do usuário no sistema.

### Entidade Docente:

- **id** (Long): Identificador único do docente.
- **nome** (String): Nome completo do docente.
- **data_entrada** (Date): Data de entrada do docente na empresa.
- **id_usuario** (Long): Identificador do usuário, único para cada docente.

### Entidade Turma:

- **id** (int): Identificador único da turma.
- **nome** (String): Nome ou identificador da turma.
- **id_curso** (Long): Identificador do curso relacionado à turma.
- **professor** (Docente): Docente responsável pela turma.
- **curso** (Curso): Curso relacionado à turma.
- **alunos** (List<Aluno>): Lista de alunos matriculados na turma.

### Entidade Aluno:

- **id** (Long): Identificador único do aluno.
- **nome** (String): Nome completo do aluno.
- **data_nascimento** (Data): Data de nascimento do aluno.
- **id_usuario** (Long): Identificador do usuário, único para cada aluno.
- **id_turma** (Long): Identificador da turma à qual o aluno está associado.
- **notas** (List<Nota>): Lista de notas do aluno.

### Entidade Curso:

- **id** (int): Identificador único do curso.
- **nome** (String): Nome do curso.
- **turmas** (List<Turma>): Lista de turmas associadas ao curso.
- **materias** (List<Materia>): Lista de matérias oferecidas no curso.

### Entidade Matéria:

- **id** (Long): Identificador único da matéria.
- **nome** (String): Nome da matéria.
- **id_curso** (Long): Identificador do curso relacionado à matéria.

### Entidade Notas:

- **id** (Long): Identificador único da nota.
- **id_aluno** (Long): Identificador do aluno associado à nota.
- **id_professor** (Long): Identificador do professor que atribuiu a nota.
- **id_materia** (Long): Identificador da matéria à qual a nota pertence.
- **valor** (double): Valor da nota.
- **data** (Date): Data em que a nota foi atribuída.

Essas entidades formam a base do sistema e são essenciais para o seu funcionamento.

Representação das entidades na modelagem do Diagrama ER (Entidade-Relacionamento).
![Diagrama ER](https://raw.githubusercontent.com/leandroCodeDev/edufullstack-gestao-educacional/main/imagens/gestao-educacional%20.png?token=GHSAT0AAAAAACQ64G5673XOEVDMBEQZSIHUZQ4MF7Q)

## Utilização de Papéis de Usuário

### Papéis de Usuário:

O sistema define diferentes papéis de usuário, cada um com permissões específicas:

1. **ADM (Administrador)**:
    - Atribuído à entidade Docente.
    - Acesso completo a todas as funcionalidades do sistema.
    - Capacidade de deletar entidades.
    - Único usuário capaz de criar novos usuários (realizar cadastros).

2. **PEDAGÓGICO (Equipe Pedagógica)**:
    - Atribuído à entidade Docente.
    - Acesso completo sobre turma, curso, professor, aluno.
    - Restrição de não poder deletar dados.

3. **RECRUITER (Recrutador)**:
    - Atribuído à entidade Docente.
    - Acesso completo sobre professor.
    - Restrição de não poder deletar dados.

4. **PROFESSOR**:
    - Atribuído à entidade Docente.
    - Acesso completo sobre notas.
    - Restrição de não poder deletar dados.

5. **ALUNO**:
    - Atribuído à entidade Aluno.
    - Acesso apenas às suas próprias notas e pontuação total pessoal.
    - Restrição de não poder deletar dados.

## Regras de Negócio

### Regras de Negócio Implementadas:

1. **Deleção de Entidades**:
    - Apenas o ADM tem permissão para deletar entidades do sistema.

2. **Criação de Usuários**:
    - Apenas o ADM tem permissão para criar novos usuários, realizando cadastros no sistema.

3. **Pontuação Total do Aluno**
    - Soma das notas do aluno.
    - Dividido pelo número de matérias.
    - Multiplicado por 10.
    - Esta pontuação total representa o desempenho geral do aluno no sistema.
4. **Logs**:
    - Os Logs são pré-requisitos para o sistema.
    - Um log é gerado para cada erro ocorrido.
    - Pelo menos um log é gerado para cada método executado.

## Endpoints de Autenticação

### Login de Usuário (POST /login)

Permite que um usuário faça login no sistema.

- **Body**: JSON contendo as credenciais do usuário (usuário e senha).
- **Respostas Possíveis**:
    - Código 200 (OK) - Login bem-sucedido. Retorna um token JWT (JSON Web Token) no corpo da resposta.
    - Código 401 (Unauthorized) - Credenciais inválidas. O usuário não está autorizado a acessar o sistema.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Cadastro de Usuário (POST /cadastro)

Permite que um novo usuário seja cadastrado no sistema.

- **Body**: JSON representando os dados do novo usuário a ser cadastrado.
- **Respostas Possíveis**:
    - Código 201 (Created) - Usuário cadastrado com sucesso. Retorna o JSON do usuário criado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

## Endpoints para a Entidade Docente

### Criar Docente (POST /docentes)

Permite criar um novo docente.

- **Body**: JSON representando os dados do docente a ser criado.
- **Respostas Possíveis**:
    - Código 201 (Created) - Docente criado com sucesso. Retorna o JSON do docente criado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Docente por ID (GET /docentes/{id})

Permite obter um docente pelo seu ID.

- **Parâmetros de URL**: ID do docente.
- **Respostas Possíveis**:
    - Código 200 (OK) - Docente encontrado. Retorna o JSON do docente no corpo da resposta.
    - Código 404 (Not Found) - Docente não encontrado.

### Atualizar Docente (PUT /docentes/{id})

Permite atualizar os dados de um docente.

- **Parâmetros de URL**: ID do docente a ser atualizado.
- **Body**: JSON representando os novos dados do docente.
- **Respostas Possíveis**:
    - Código 200 (OK) - Docente atualizado com sucesso. Retorna o JSON do docente atualizado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Docente não encontrado.

### Excluir Docente (DELETE /docentes/{id})

Permite excluir um docente.

- **Parâmetros de URL**: ID do docente a ser excluído.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Docente excluído com sucesso.
    - Código 404 (Not Found) - Docente não encontrado.

### Listar Docentes (GET /docentes)

Permite listar todos os docentes cadastrados no sistema.

- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todos os docentes no corpo da resposta.
    - Código 404 (Not Found) - Não há docentes cadastrados.

## Endpoints para a Entidade Turma

### Criar Turma (POST /turmas)

Permite criar uma nova turma.

- **Body**: JSON representando os dados da turma a ser criada.
- **Respostas Possíveis**:
    - Código 201 (Created) - Turma criada com sucesso. Retorna o JSON da turma criada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Turma por ID (GET /turmas/{id})

Permite obter uma turma pelo seu ID.

- **Parâmetros de URL**: ID da turma.
- **Respostas Possíveis**:
    - Código 200 (OK) - Turma encontrada. Retorna o JSON da turma no corpo da resposta.
    - Código 404 (Not Found) - Turma não encontrada.

### Atualizar Turma (PUT /turmas/{id})

Permite atualizar os dados de uma turma.

- **Parâmetros de URL**: ID da turma a ser atualizada.
- **Body**: JSON representando os novos dados da turma.
- **Respostas Possíveis**:
    - Código 200 (OK) - Turma atualizada com sucesso. Retorna o JSON da turma atualizada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Turma não encontrada.

### Excluir Turma (DELETE /turmas/{id})

Permite excluir uma turma.

- **Parâmetros de URL**: ID da turma a ser excluída.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Turma excluída com sucesso.
    - Código 404 (Not Found) - Turma não encontrada.

### Listar Turmas (GET /turmas)

Permite listar todas as turmas cadastradas no sistema.

- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todas as turmas no corpo da resposta.
    - Código 404 (Not Found) - Não há turmas cadastradas.

## Endpoints para a Entidade Aluno

### Criar Aluno (POST /alunos)

Permite criar um novo aluno.

- **Body**: JSON representando os dados do aluno a ser criado.
- **Respostas Possíveis**:
    - Código 201 (Created) - Aluno criado com sucesso. Retorna o JSON do aluno criado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Aluno por ID (GET /alunos/{id})

Permite obter um aluno pelo seu ID.

- **Parâmetros de URL**: ID do aluno.
- **Respostas Possíveis**:
    - Código 200 (OK) - Aluno encontrado. Retorna o JSON do aluno no corpo da resposta.
    - Código 404 (Not Found) - Aluno não encontrado.

### Atualizar Aluno (PUT /alunos/{id})

Permite atualizar os dados de um aluno.

- **Parâmetros de URL**: ID do aluno a ser atualizado.
- **Body**: JSON representando os novos dados do aluno.
- **Respostas Possíveis**:
    - Código 200 (OK) - Aluno atualizado com sucesso. Retorna o JSON do aluno atualizado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Aluno não encontrado.

### Excluir Aluno (DELETE /alunos/{id})

Permite excluir um aluno.

- **Parâmetros de URL**: ID do aluno a ser excluído.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Aluno excluído com sucesso.
    - Código 404 (Not Found) - Aluno não encontrado.

### Listar Alunos (GET /alunos)

Permite listar todos os alunos cadastrados no sistema.

- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todos os alunos no corpo da resposta.
    - Código 404 (Not Found) - Não há alunos cadastrados.

### Listar Notas por Aluno (GET /alunos/{id_aluno}/notas)

Permite listar todas as notas de um aluno específico.

- **Parâmetros de URL**: ID do aluno.
- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todas as notas do aluno no corpo da resposta.
    - Código 404 (Not Found) - Não há notas cadastradas para o aluno especificado.

### Pontuação Total do Aluno (GET /alunos/{id}/pontuacao)

Calcula e retorna a pontuação total de um aluno.

- **Respostas Possíveis**:
    - Código 200 (OK) - Pontuação calculada com sucesso. Retorna o JSON com o campo "pontuação" preenchido.
    - Código 404 (Not Found) - Aluno não encontrado.

## Endpoints para a Entidade Curso

### Criar Curso (POST /cursos)

Permite criar um novo curso.

- **Body**: JSON representando os dados do curso a ser criado.
- **Respostas Possíveis**:
    - Código 201 (Created) - Curso criado com sucesso. Retorna o JSON do curso criado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Curso por ID (GET /cursos/{id})

Permite obter um curso pelo seu ID.

- **Parâmetros de URL**: ID do curso.
- **Respostas Possíveis**:
    - Código 200 (OK) - Curso encontrado. Retorna o JSON do curso no corpo da resposta.
    - Código 404 (Not Found) - Curso não encontrado.

### Atualizar Curso (PUT /cursos/{id})

Permite atualizar os dados de um curso.

- **Parâmetros de URL**: ID do curso a ser atualizado.
- **Body**: JSON representando os novos dados do curso.
- **Respostas Possíveis**:
    - Código 200 (OK) - Curso atualizado com sucesso. Retorna o JSON do curso atualizado no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Curso não encontrado.

### Excluir Curso (DELETE /cursos/{id})

Permite excluir um curso.

- **Parâmetros de URL**: ID do curso a ser excluído.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Curso excluído com sucesso.
    - Código 404 (Not Found) - Curso não encontrado.

### Listar Cursos (GET /cursos)

Permite listar todos os cursos cadastrados no sistema.

- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todos os cursos no corpo da resposta.
    - Código 404 (Not Found) - Não há cursos cadastrados.

### Listar Matérias por Curso (GET /cursos/{id_curso}/materias)

Permite listar todas as matérias de um curso específico.

- **Parâmetros de URL**: ID do curso.
- **Respostas Possíveis**:
    - Código 200 (OK) - Retorna uma lista de todas as matérias do curso no corpo da resposta.
    - Código 404 (Not Found) - Não há matérias cadastradas para o curso especificado.

## Endpoints para a Entidade Matéria

### Criar Matéria (POST /materias)

Permite criar uma nova matéria.

- **Body**: JSON representando os dados da matéria a ser criada.
- **Respostas Possíveis**:
    - Código 201 (Created) - Matéria criada com sucesso. Retorna o JSON da matéria criada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Matéria por ID (GET /materias/{id})

Permite obter uma matéria pelo seu ID.

- **Parâmetros de URL**: ID da matéria.
- **Respostas Possíveis**:
    - Código 200 (OK) - Matéria encontrada. Retorna o JSON da matéria no corpo da resposta.
    - Código 404 (Not Found) - Matéria não encontrada.

### Atualizar Matéria (PUT /materias/{id})

Permite atualizar os dados de uma matéria.

- **Parâmetros de URL**: ID da matéria a ser atualizada.
- **Body**: JSON representando os novos dados da matéria.
- **Respostas Possíveis**:
    - Código 200 (OK) - Matéria atualizada com sucesso. Retorna o JSON da matéria atualizada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Matéria não encontrada.

### Excluir Matéria (DELETE /materias/{id})

Permite excluir uma matéria.

- **Parâmetros de URL**: ID da matéria a ser excluída.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Matéria excluída com sucesso.
    - Código 404 (Not Found) - Matéria não encontrada.

### Criar Nota (POST /notas)

Permite criar uma nova nota.

- **Body**: JSON representando os dados da nota a ser criada.
- **Respostas Possíveis**:
    - Código 201 (Created) - Nota criada com sucesso. Retorna o JSON da nota criada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.

### Obter Nota por ID (GET /notas/{id})

Permite obter uma nota pelo seu ID.

- **Parâmetros de URL**: ID da nota.
- **Respostas Possíveis**:
    - Código 200 (OK) - Nota encontrada. Retorna o JSON da nota no corpo da resposta.
    - Código 404 (Not Found) - Nota não encontrada.

### Atualizar Nota (PUT /notas/{id})

Permite atualizar os dados de uma nota.

- **Parâmetros de URL**: ID da nota a ser atualizada.
- **Body**: JSON representando os novos dados da nota.
- **Respostas Possíveis**:
    - Código 200 (OK) - Nota atualizada com sucesso. Retorna o JSON da nota atualizada no corpo da resposta.
    - Código 400 (Bad Request) - Requisição inválida, por exemplo, dados ausentes ou incorretos.
    - Código 404 (Not Found) - Nota não encontrada.

### Excluir Nota (DELETE /notas/{id})

Permite excluir uma nota.

- **Parâmetros de URL**: ID da nota a ser excluída.
- **Respostas Possíveis**:
    - Código 204 (No Content) - Nota excluída com sucesso.
    - Código 404 (Not Found) - Nota não encontrada.



