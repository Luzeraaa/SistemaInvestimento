CREATE TABLE conta (
    id BIGINT NOT NULL PRIMARY KEY,
    numero INT NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true,
    investidor_pf_id BIGINT,
    investidor_pj_id BIGINT,
    FOREIGN KEY (investidor_pf_id) REFERENCES investidor_pessoa_fisica (id),
    FOREIGN KEY (investidor_pj_id) REFERENCES investidor_pessoa_juridica (id)
);
