-- ============================================================
-- V1__criar_schema_inicial.sql
-- Schema inicial do sistema Duarte Palace
-- Ordem de criação respeita as dependências de chave estrangeira
-- ============================================================

-- ============================================================
-- 1. USUARIO
-- Não depende de nenhuma outra tabela
-- ============================================================
CREATE TABLE usuario (
                         id       BIGSERIAL PRIMARY KEY,
                         username VARCHAR(50)  NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role     VARCHAR(30)  NOT NULL
);

-- ============================================================
-- 2. QUARTO
-- Não depende de nenhuma outra tabela
-- ============================================================
CREATE TABLE quarto (
                        id             BIGSERIAL PRIMARY KEY,
                        numero         VARCHAR(10)    NOT NULL UNIQUE,
                        tipo           VARCHAR(50)    NOT NULL,
                        capacidade     INTEGER        NOT NULL,
                        preco_diaria   NUMERIC(10,2)  NOT NULL,
                        em_manutencao  BOOLEAN        NOT NULL DEFAULT FALSE
);

-- ============================================================
-- 3. HOSPEDE
-- Não depende de nenhuma outra tabela
-- ============================================================
CREATE TABLE hospede (
                         id        BIGSERIAL PRIMARY KEY,
                         nome      VARCHAR(150) NOT NULL,
                         cpf       VARCHAR(14)  NOT NULL UNIQUE,
                         telefone  VARCHAR(20)  NOT NULL,
                         email     VARCHAR(150) NOT NULL UNIQUE
);

-- ============================================================
-- 4. RESERVA
-- Depende de: quarto, hospede
-- ============================================================
CREATE TABLE reserva (
                         id            BIGSERIAL PRIMARY KEY,
                         data_entrada  DATE          NOT NULL,
                         data_saida    DATE          NOT NULL,
                         preco_diaria  NUMERIC(10,2) NOT NULL,
                         status        VARCHAR(20)   NOT NULL,
                         quarto_id     BIGINT        NOT NULL,
                         hospede_id    BIGINT        NOT NULL,

                         CONSTRAINT fk_reserva_quarto
                             FOREIGN KEY (quarto_id) REFERENCES quarto (id)
                                 ON DELETE RESTRICT,

                         CONSTRAINT fk_reserva_hospede
                             FOREIGN KEY (hospede_id) REFERENCES hospede (id)
                                 ON DELETE RESTRICT
);

-- ============================================================
-- 5. HOSPEDAGEM
-- Depende de: quarto, hospede, reserva (nullable - walk-in)
-- ============================================================
CREATE TABLE hospedagem (
                            id                    BIGSERIAL PRIMARY KEY,
                            check_in              TIMESTAMP NOT NULL,
                            data_saida_prevista   DATE      NOT NULL,
                            check_out_real        TIMESTAMP,
                            quarto_id             BIGINT    NOT NULL,
                            hospede_id            BIGINT    NOT NULL,
                            reserva_id            BIGINT,

                            CONSTRAINT fk_hospedagem_quarto
                                FOREIGN KEY (quarto_id) REFERENCES quarto (id)
                                    ON DELETE RESTRICT,

                            CONSTRAINT fk_hospedagem_hospede
                                FOREIGN KEY (hospede_id) REFERENCES hospede (id)
                                    ON DELETE RESTRICT,

                            CONSTRAINT fk_hospedagem_reserva
                                FOREIGN KEY (reserva_id) REFERENCES reserva (id)
                                    ON DELETE RESTRICT
);

-- ============================================================
-- 6. CONSUMO
-- Depende de: hospedagem
-- ============================================================
CREATE TABLE consumo (
                         id            BIGSERIAL PRIMARY KEY,
                         descricao     VARCHAR(150)  NOT NULL,
                         valor         NUMERIC(10,2) NOT NULL,
                         data_consumo  TIMESTAMP     NOT NULL,
                         hospedagem_id BIGINT        NOT NULL,

                         CONSTRAINT fk_consumo_hospedagem
                             FOREIGN KEY (hospedagem_id) REFERENCES hospedagem (id)
                                 ON DELETE RESTRICT
);

-- ============================================================
-- 7. PAGAMENTO
-- Depende de: hospedagem
-- ============================================================
CREATE TABLE pagamento (
                           id               BIGSERIAL PRIMARY KEY,
                           valor            NUMERIC(10,2) NOT NULL,
                           data_pagamento   TIMESTAMP     NOT NULL,
                           forma_pagamento  VARCHAR(20)   NOT NULL,
                           tipo_pagamento   VARCHAR(20)   NOT NULL,
                           valor_retido     NUMERIC(10,2),
                           hospedagem_id    BIGINT        NOT NULL,

                           CONSTRAINT fk_pagamento_hospedagem
                               FOREIGN KEY (hospedagem_id) REFERENCES hospedagem (id)
                                   ON DELETE RESTRICT
);