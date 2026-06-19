package dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.repository;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.StatusMissao;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoInimigo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoTerreno;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.entity.MissaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositório de Missões — Spring Data JPA (Port de saída da Clean Architecture).
 *
 * Queries via JPQL nomeado (@Query) para evitar SQL injection (CR-03 preventivo).
 * Parâmetros sempre ligados por nome (@Param) — nunca concatenação de String.
 */
public interface MissaoRepository extends JpaRepository<MissaoEntity, Long> {

    // ── Queries por filtro individual ──────────────────────────────────

    Page<MissaoEntity> findByStatusMissao(StatusMissao status, Pageable pageable);

    Page<MissaoEntity> findByTipoInimigo(TipoInimigo tipoInimigo, Pageable pageable);

    Page<MissaoEntity> findByTipoTerreno(TipoTerreno tipoTerreno, Pageable pageable);

    List<MissaoEntity> findByCapituloId(Long capituloId);

    // ── Query combinada com filtros opcionais (defesa anti SQL injection) ──
    /**
     * Busca missões com filtros opcionais. Parâmetros null são ignorados.
     * JPQL não permite SQL injection — parâmetros são sempre tipados.
     */
    @Query("""
        SELECT m FROM MissaoEntity m
        WHERE (:status      IS NULL OR m.statusMissao = :status)
          AND (:tipoInimigo IS NULL OR m.tipoInimigo  = :tipoInimigo)
          AND (:tipoTerreno IS NULL OR m.tipoTerreno  = :tipoTerreno)
          AND (:capituloId  IS NULL OR m.capitulo.id  = :capituloId)
        ORDER BY m.dataInicio DESC
        """)
    Page<MissaoEntity> buscarComFiltros(
        @Param("status")      StatusMissao status,
        @Param("tipoInimigo") TipoInimigo  tipoInimigo,
        @Param("tipoTerreno") TipoTerreno  tipoTerreno,
        @Param("capituloId")  Long         capituloId,
        Pageable pageable
    );

    // ── Query de alerta: missões com SOS ativo pendente ───────────────
    @Query("""
        SELECT m FROM MissaoEntity m
        WHERE m.sinalSOS.ativo = true
          AND m.sinalSOS.respondido = false
        ORDER BY m.sinalSOS.timestampRecebido ASC
        """)
    List<MissaoEntity> buscarMissoesComSosPendente();
}
