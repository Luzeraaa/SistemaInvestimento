CREATE TABLE investidor (
    id BIGINT NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento TIMESTAMP NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);
