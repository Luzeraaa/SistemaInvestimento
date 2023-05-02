CREATE TABLE investidor_pessoa_fisica (
    id BIGINT NOT NULL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    investidor_id BIGINT NOT NULL,
    FOREIGN KEY (investidor_id) REFERENCES investidor (id)
);