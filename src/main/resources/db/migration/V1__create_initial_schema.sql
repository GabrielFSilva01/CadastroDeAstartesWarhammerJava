-- ============================================================
-- V1__create_initial_schema.sql
-- Cogitator Imperialis — Schema inicial (entidades do legado)
-- ============================================================

-- Capítulos Space Marines
CREATE TABLE IF NOT EXISTS tb_capitulos (
    id                  BIGSERIAL       PRIMARY KEY,
    nome                VARCHAR(100)    NOT NULL,
    descricao           VARCHAR(500),
    localidade          VARCHAR(200),
    quantidade_missoes  INTEGER         NOT NULL DEFAULT 0,
    numero_soldados     INTEGER         NOT NULL DEFAULT 0
);

-- Soldados (vinculados a um Capítulo)
CREATE TABLE IF NOT EXISTS tb_soldados (
    id              BIGSERIAL       PRIMARY KEY,
    nome            VARCHAR(100)    NOT NULL,
    descricao       VARCHAR(500),
    altura          DOUBLE PRECISION,
    capitulo_id     BIGINT
        REFERENCES tb_capitulos(id) ON DELETE SET NULL
);

-- Acessórios (vinculados a um Soldado)
CREATE TABLE IF NOT EXISTS tb_acessorios (
    id          BIGSERIAL       PRIMARY KEY,
    nome        VARCHAR(255)    NOT NULL,
    descricao   VARCHAR(500),
    proposito   VARCHAR(200),
    soldado_id  BIGINT
        REFERENCES tb_soldados(id) ON DELETE CASCADE
);

-- Índices de suporte às queries do legado
CREATE INDEX IF NOT EXISTS idx_soldados_capitulo ON tb_soldados(capitulo_id);
CREATE INDEX IF NOT EXISTS idx_acessorios_soldado ON tb_acessorios(soldado_id);
