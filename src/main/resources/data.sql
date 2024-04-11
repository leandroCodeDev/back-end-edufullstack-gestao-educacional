-- Inserir os dados na tabela perfil apenas se não existirem na tabela
INSERT INTO perfil (id, nome)
SELECT id, nome
FROM (VALUES
    (1, 'ADM'),
    (2, 'PEDAGOGICO'),
    (3, 'RECRUITER'),
    (4, 'PROFESSOR'),
    (5, 'ALUNO')
) AS dados(id, nome)
WHERE NOT EXISTS (
    SELECT 1 FROM perfil WHERE perfil.id = dados.id
);