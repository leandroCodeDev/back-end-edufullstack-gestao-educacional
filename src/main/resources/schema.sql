CREATE TABLE IF NOT EXISTS perfil (
	id bigserial NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT perfil_pkey PRIMARY KEY (id),
	CONSTRAINT uk_8m46w0jj2ksw0subwvu0i5kmd UNIQUE (nome)
);