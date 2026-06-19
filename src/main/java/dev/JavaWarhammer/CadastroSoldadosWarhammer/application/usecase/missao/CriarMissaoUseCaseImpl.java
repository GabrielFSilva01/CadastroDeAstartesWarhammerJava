package dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.CriarMissaoRequest;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.MissaoResponse;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.exception.MissaoNaoEncontradaException;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.entity.MissaoEntity;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.repository.MissaoRepository;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Capitulo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementação do Use Case de criação de missão.
 *
 * Camada: Application — orquestra domínio e infraestrutura.
 * Segurança: @PreAuthorize será adicionado na Fase 2 (JWT + RBAC).
 *
 * Defesa SQL Injection: toda persistência passa pelo JpaRepository
 * com queries JPQL parametrizadas — zero SQL concatenado.
 */
@Service
@Transactional
public class CriarMissaoUseCaseImpl implements CriarMissaoUseCase {

    private final MissaoRepository missaoRepository;

    // CapituloRepository será introduzido na Fase 3 com a nova entidade.
    // Por ora, usamos EntityManager para validar FK sem expor o repository legado.
    private final jakarta.persistence.EntityManager entityManager;

    public CriarMissaoUseCaseImpl(MissaoRepository missaoRepository,
                                  jakarta.persistence.EntityManager entityManager) {
        this.missaoRepository = missaoRepository;
        this.entityManager    = entityManager;
    }

    @Override
    public MissaoResponse executar(CriarMissaoRequest request) {

        // 1. Valida que o Capítulo existe (FK obrigatória na MissaoEntity)
        Capitulo capitulo = entityManager.find(Capitulo.class, request.capituloId());
        if (capitulo == null) {
            throw new MissaoNaoEncontradaException(
                "Capítulo não encontrado com o ID: " + request.capituloId() +
                ". Não é possível criar uma missão sem um Capítulo responsável."
            );
        }

        // 2. Constrói a entidade a partir do request (mapeamento explícito — sem MapStruct por ora)
        MissaoEntity novaMissao = new MissaoEntity();
        novaMissao.setNome(request.nome());
        novaMissao.setDescricao(request.descricao());
        novaMissao.setNivelSigilo(request.nivelSigilo());
        novaMissao.setTipoInimigo(request.tipoInimigo());
        novaMissao.setTipoTerreno(request.tipoTerreno());
        novaMissao.setStatusCivis(request.statusCivis());
        novaMissao.setDataInicio(request.dataInicio());
        novaMissao.setCapitulo(capitulo);
        // statusMissao e sinalSOS são inicializados via @PrePersist com defaults seguros

        // 3. Persiste (Flyway garante o schema — sem ddl-auto=create)
        MissaoEntity salva = missaoRepository.save(novaMissao);

        // 4. Mapeia para DTO de resposta (nunca expõe a entidade JPA diretamente)
        return MissaoResponse.from(salva);
    }
}
