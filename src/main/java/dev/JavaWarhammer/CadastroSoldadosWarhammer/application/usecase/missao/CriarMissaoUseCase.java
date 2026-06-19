package dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.CriarMissaoRequest;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.MissaoResponse;

/**
 * Port de entrada (Use Case) para criação de Missões.
 *
 * Esta interface é o único ponto de contato entre a camada Adapter (Controller)
 * e a camada Application. O Controller não conhece a implementação.
 *
 * Segurança: protegido por @PreAuthorize na implementação.
 * Roles autorizadas: ROLE_PRIMARCA, ROLE_REPRESENTANTE.
 */
public interface CriarMissaoUseCase {

    /**
     * Registra uma nova missão operacional no Cogitator.
     *
     * @param request  Dados validados da requisição HTTP
     * @return         DTO da missão criada, com ID gerado pelo banco
     *
     * @throws dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.MissaoNaoEncontradaException
     *         se o capituloId não corresponder a um Capítulo existente
     * @throws dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.AcessoNegadoException
     *         se o executor não possuir role suficiente (fase 2)
     */
    MissaoResponse executar(CriarMissaoRequest request);
}
