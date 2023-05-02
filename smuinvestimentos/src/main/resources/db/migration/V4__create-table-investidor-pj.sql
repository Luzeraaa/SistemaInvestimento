CREATE TABLE investidor_pessoa_juridica (
    id BIGINT NOT NULL PRIMARY KEY,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    investidor_id BIGINT NOT NULL,
    FOREIGN KEY (investidor_id) REFERENCES investidor (id)
);