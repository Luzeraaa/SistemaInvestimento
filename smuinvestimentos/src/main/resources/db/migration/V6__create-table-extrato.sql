CREATE TABLE extrato (
    id BIGINT NOT NULL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    dataOperacao DATE NOT NULL,
    valorOperacao DECIMAL(10,2) NOT NULL,
    conta_id BIGINT NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES conta (id)
);
