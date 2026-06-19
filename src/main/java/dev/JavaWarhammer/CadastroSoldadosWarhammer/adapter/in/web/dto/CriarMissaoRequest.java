package dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO de entrada para criação de uma Missão.
 *
 * Java record — imutável por natureza, ideal para DTOs de request.
 * Validado pelo Bean Validation antes de chegar ao Use Case.
 */
@Schema(description = "Payload para criação de uma nova missão operacional.")
public record CriarMissaoRequest(

    @NotBlank(message = "O nome da missão é obrigatório.")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
    @Schema(description = "Nome código da operação.", example = "Operação Tempestade Carmesim")
    String nome,

    @Size(max = 2000, message = "A descrição não pode exceder 2000 caracteres.")
    @Schema(description = "Briefing tático completo da operação.",
            example = "Neutralizar posto avançado Tiranídeo no Setor 7G da Colmeia Tertius.")
    String descricao,

    @NotNull(message = "O nível de sigilo é obrigatório.")
    @Schema(description = "Classificação de sigilo da operação.",
            example = "VERMELHO",
            allowableValues = {"VERDE", "AMARELO", "VERMELHO", "NEGRO"})
    NivelSigilo nivelSigilo,

    @NotNull(message = "O tipo de inimigo é obrigatório.")
    @Schema(description = "Facção inimiga identificada.", example = "TIRANIDA")
    TipoInimigo tipoInimigo,

    @NotNull(message = "O tipo de terreno é obrigatório.")
    @Schema(description = "Classificação do teatro de operações.", example = "URBANO")
    TipoTerreno tipoTerreno,

    @NotNull(message = "O status de civis é obrigatório.")
    @Schema(description = "Presença e condição de civis na área de combate.", example = "EM_RISCO")
    StatusCivis statusCivis,

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início não pode ser no passado.")
    @Schema(description = "Data de início da operação (ISO 8601).", example = "2026-07-01")
    LocalDate dataInicio,

    @NotNull(message = "O ID do Capítulo responsável é obrigatório.")
    @Positive(message = "O ID do Capítulo deve ser um número positivo.")
    @Schema(description = "ID do Capítulo Space Marine responsável pela missão.", example = "1")
    Long capituloId

) {}
