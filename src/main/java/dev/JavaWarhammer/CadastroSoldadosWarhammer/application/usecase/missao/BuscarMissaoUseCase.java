package dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.MissaoResponse;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.StatusMissao;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoInimigo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoTerreno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Port de entrada (Use Case) para consultas de Missões.
 *
 * Separa responsabilidade de leitura da escrita (CQRS parcial).
 * Roles autorizadas: ROLE_SOLDADO e superior (filtrado por NivelSigilo).
 */
public interface BuscarMissaoUseCase {

    /**
     * Busca uma missão pelo ID.
     * O acesso é filtrado pelo NivelSigilo: missões NEGRO só retornam para ROLE_PRIMARCA.
     *
     * @param id ID da missão
     * @return   DTO da missão encontrada
     * @throws dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.MissaoNaoEncontradaException
     *         se o ID não existir
     * @throws dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.AcessoNegadoException
     *         se o sigilo exceder o clearance do executor (fase 2)
     */
    MissaoResponse buscarPorId(Long id);

    /**
     * Lista missões com filtros opcionais e paginação.
     * Parâmetros null são ignorados (todos os valores aceitos).
     *
     * @param status       Filtrar por status (nullable)
     * @param tipoInimigo  Filtrar por inimigo (nullable)
     * @param tipoTerreno  Filtrar por terreno (nullable)
     * @param capituloId   Filtrar por capítulo (nullable)
     * @param pageable     Configuração de paginação e ordenação
     * @return             Página de resultados
     */
    Page<MissaoResponse> listarComFiltros(
        StatusMissao status,
        TipoInimigo  tipoInimigo,
        TipoTerreno  tipoTerreno,
        Long         capituloId,
        Pageable     pageable
    );
}
