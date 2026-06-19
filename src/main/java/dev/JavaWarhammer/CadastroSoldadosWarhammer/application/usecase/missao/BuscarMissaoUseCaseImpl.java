package dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.MissaoResponse;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.StatusMissao;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoInimigo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoTerreno;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.MissaoNaoEncontradaException;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.repository.MissaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do Use Case de consulta de missões.
 *
 * Camada: Application — apenas leitura (@Transactional readOnly).
 * Segurança: filtro por NivelSigilo será integrado na Fase 2.
 *
 * A query combinada usa JPQL parametrizado (MissaoRepository#buscarComFiltros)
 * — imune a SQL Injection por design.
 */
@Service
@Transactional(readOnly = true)
public class BuscarMissaoUseCaseImpl implements BuscarMissaoUseCase {

    private final MissaoRepository missaoRepository;

    public BuscarMissaoUseCaseImpl(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    @Override
    public MissaoResponse buscarPorId(Long id) {
        return missaoRepository.findById(id)
            .map(MissaoResponse::from)
            .orElseThrow(() -> new MissaoNaoEncontradaException(id));
    }

    @Override
    public Page<MissaoResponse> listarComFiltros(
            StatusMissao status,
            TipoInimigo  tipoInimigo,
            TipoTerreno  tipoTerreno,
            Long         capituloId,
            Pageable     pageable) {

        // Delega para a query JPQL parametrizada do repositório.
        // Parâmetros null são tratados como "sem filtro" na query.
        return missaoRepository
            .buscarComFiltros(status, tipoInimigo, tipoTerreno, capituloId, pageable)
            .map(MissaoResponse::from);
    }
}
