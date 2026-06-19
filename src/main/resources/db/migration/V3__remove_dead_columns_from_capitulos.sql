-- ============================================================
-- V3__remove_dead_columns_from_capitulos.sql
-- Cogitator Imperialis — Fase 1 Code Review (Poda)
-- Remove campos corruptos de tb_capitulos:
--   quantidade_missoes: derivável via JOIN com tb_missoes
--   numero_soldados:    derivável via JOIN com tb_soldados
-- ============================================================

-- Remove apenas se as colunas existirem (idempotente)
ALTER TABLE tb_capitulos
    DROP COLUMN IF EXISTS quantidade_missoes,
    DROP COLUMN IF EXISTS numero_soldados;

COMMENT ON TABLE tb_capitulos IS
    'Capítulos Space Marine — contadores derivados removidos na Fase 1 (dados em tb_missoes e tb_soldados)';
