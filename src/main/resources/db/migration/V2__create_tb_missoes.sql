-- ============================================================
-- V2__create_tb_missoes.sql
-- Cogitator Imperialis — Tabela de Missões expandida (Fase 1)
-- Substitui a entidade legada 'Missoes' removida na Fase 1.
-- ============================================================

CREATE TABLE IF NOT EXISTS tb_missoes (
    id              BIGSERIAL       PRIMARY KEY,

    -- Identificação
    nome            VARCHAR(150)    NOT NULL,
    descricao       TEXT,

    -- Classificação Operacional (Enums como VARCHAR — EnumType.STRING)
    nivel_sigilo    VARCHAR(15)     NOT NULL,
    tipo_inimigo    VARCHAR(25)     NOT NULL,
    tipo_terreno    VARCHAR(20)     NOT NULL,
    status_civis    VARCHAR(20)     NOT NULL DEFAULT 'AUSENTE',
    status_missao   VARCHAR(20)     NOT NULL DEFAULT 'PLANEJADA',

    -- SOS Embutido (Value Object @Embeddable)
    sos_ativo           BOOLEAN         NOT NULL DEFAULT FALSE,
    sos_origem          VARCHAR(30),
    sos_coordenadas     VARCHAR(150),
    sos_timestamp       TIMESTAMP,
    sos_respondido      BOOLEAN         NOT NULL DEFAULT FALSE,

    -- Temporal
    data_inicio         DATE            NOT NULL,
    data_conclusao      DATE,
    criado_em           TIMESTAMP       NOT NULL DEFAULT NOW(),

    -- Relacionamento com Capítulo (obrigatório)
    capitulo_id     BIGINT          NOT NULL
        REFERENCES tb_capitulos(id) ON DELETE RESTRICT
);

-- Índices para as queries do MissaoRepository
CREATE INDEX IF NOT EXISTS idx_missao_status       ON tb_missoes(status_missao);
CREATE INDEX IF NOT EXISTS idx_missao_tipo_inimigo ON tb_missoes(tipo_inimigo);
CREATE INDEX IF NOT EXISTS idx_missao_capitulo     ON tb_missoes(capitulo_id);

-- Comentários documentais no schema
COMMENT ON TABLE  tb_missoes IS 'Missões operacionais do Cogitator Imperialis — Fase 1';
COMMENT ON COLUMN tb_missoes.sos_ativo     IS 'SOS ativado para esta missão';
COMMENT ON COLUMN tb_missoes.sos_respondido IS 'Forças já enviadas ao local do SOS';
