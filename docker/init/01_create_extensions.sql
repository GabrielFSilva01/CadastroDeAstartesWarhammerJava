-- ============================================================
-- docker/init/01_create_extensions.sql
-- Executado AUTOMATICAMENTE pelo PostgreSQL na primeira
-- inicialização do container (volume vazio).
--
-- NÃO é executado pelo Flyway — é pré-Flyway.
-- Cria extensões que precisam existir antes das migrations.
-- ============================================================

-- UUID support (para IDs de futuros tokens JWT e simulações)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Busca textual avançada em português (futura busca de missões por texto)
CREATE EXTENSION IF NOT EXISTS "unaccent";

-- Estatísticas estendidas (otimização de queries complexas do simulador)
CREATE EXTENSION IF NOT EXISTS "pg_stat_statements";
