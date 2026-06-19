package dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.*;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.entity.MissaoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de resposta da API para Missão.
 *
 * Java record — garante que nenhuma entidade JPA vaze para a camada HTTP.
 * O factory method {@code from()} encapsula o mapeamento.
 */
@Schema(description = "Representação de uma missão operacional retornada pela API.")
public record MissaoResponse(

    @Schema(description = "Identificador único da missão.", example = "42")
    Long id,

    @Schema(description = "Nome código da operação.", example = "Operação Tempestade Carmesim")
    String nome,

    @Schema(description = "Briefing tático completo.")
    String descricao,

    @Schema(description = "Nível de sigilo da operação.")
    NivelSigilo nivelSigilo,

    @Schema(description = "Facção inimiga identificada.")
    TipoInimigo tipoInimigo,

    @Schema(description = "Teatro de operações.")
    TipoTerreno tipoTerreno,

    @Schema(description = "Status de civis na área.")
    StatusCivis statusCivis,

    @Schema(description = "Status atual da missão.")
    StatusMissao statusMissao,

    @Schema(description = "Indica se há um SOS ativo e não respondido.", example = "false")
    boolean temSosPendente,

    @Schema(description = "Data de início da operação.", example = "2026-07-01")
    LocalDate dataInicio,

    @Schema(description = "Data de conclusão (null se em aberto).", example = "null")
    LocalDate dataConclusao,

    @Schema(description = "Nome do Capítulo responsável.", example = "Ultramarines")
    String nomeCapitulo,

    @Schema(description = "Timestamp de criação do registro (ISO 8601).")
    LocalDateTime criadoEm

) {
    /**
     * Factory method — única forma de construir um MissaoResponse.
     * Garante que o mapeamento de entidade para DTO seja centralizado.
     *
     * @param entity Entidade JPA persistida
     * @return       DTO pronto para serialização HTTP
     */
    public static MissaoResponse from(MissaoEntity entity) {
        return new MissaoResponse(
            entity.getId(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getNivelSigilo(),
            entity.getTipoInimigo(),
            entity.getTipoTerreno(),
            entity.getStatusCivis(),
            entity.getStatusMissao(),
            entity.temSosAtivoPendente(),
            entity.getDataInicio(),
            entity.getDataConclusao(),
            entity.getCapitulo() != null ? entity.getCapitulo().getNome() : null,
            entity.getCriadoEm()
        );
    }
}
