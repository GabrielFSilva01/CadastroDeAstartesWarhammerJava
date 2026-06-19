package dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.CriarMissaoRequest;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.adapter.in.web.dto.MissaoResponse;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao.BuscarMissaoUseCase;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.application.usecase.missao.CriarMissaoUseCase;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.StatusMissao;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoInimigo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.TipoTerreno;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST de Missões — Adapter de entrada HTTP.
 *
 * Responsabilidade única: traduzir HTTP ↔ Use Cases.
 * Não contém lógica de negócio — delega tudo às interfaces de Use Case.
 *
 * Segurança: configurada via SecurityFilterChain (Fase 2).
 * A anotação @SecurityRequirement documenta o requisito no Swagger.
 */
@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "Gestão de operações táticas do Cogitator Imperialis")
public class MissaoController {

    private final CriarMissaoUseCase criarMissaoUseCase;
    private final BuscarMissaoUseCase buscarMissaoUseCase;

    public MissaoController(CriarMissaoUseCase criarMissaoUseCase,
                            BuscarMissaoUseCase buscarMissaoUseCase) {
        this.criarMissaoUseCase  = criarMissaoUseCase;
        this.buscarMissaoUseCase = buscarMissaoUseCase;
    }

    // ── POST /missoes ──────────────────────────────────────────────────

    @PostMapping
    @Operation(
        summary     = "Criar nova missão",
        description = "Registra uma nova operação tática. Requer ROLE_REPRESENTANTE ou superior.",
        security    = @SecurityRequirement(name = "Bearer Auth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Missão criada com sucesso.",
            content = @Content(schema = @Schema(implementation = MissaoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Payload inválido — verifique os campos.",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "403", description = "Acesso negado — Role insuficiente.",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Capítulo não encontrado.",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<MissaoResponse> criar(
            @RequestBody @Valid CriarMissaoRequest request) {

        MissaoResponse resposta = criarMissaoUseCase.executar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // ── GET /missoes/{id} ──────────────────────────────────────────────

    @GetMapping("/{id}")
    @Operation(
        summary     = "Buscar missão por ID",
        description = "Retorna detalhes de uma missão. Acesso filtrado pelo NivelSigilo.",
        security    = @SecurityRequirement(name = "Bearer Auth")
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Missão encontrada.",
            content = @Content(schema = @Schema(implementation = MissaoResponse.class))),
        @ApiResponse(responseCode = "403", description = "Clearance insuficiente para este sigilo.",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Missão não encontrada.",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<MissaoResponse> buscarPorId(
            @PathVariable @Positive(message = "O ID deve ser um número positivo.") Long id) {

        return ResponseEntity.ok(buscarMissaoUseCase.buscarPorId(id));
    }

    // ── GET /missoes ───────────────────────────────────────────────────

    @GetMapping
    @Operation(
        summary     = "Listar missões",
        description = "Lista missões com filtros opcionais por status, inimigo, terreno e capítulo.",
        security    = @SecurityRequirement(name = "Bearer Auth")
    )
    @ApiResponse(responseCode = "200", description = "Lista paginada de missões.")
    public ResponseEntity<Page<MissaoResponse>> listar(
            @Parameter(description = "Filtrar por status da missão.")
            @RequestParam(required = false) StatusMissao status,

            @Parameter(description = "Filtrar por tipo de inimigo.")
            @RequestParam(required = false) TipoInimigo tipoInimigo,

            @Parameter(description = "Filtrar por tipo de terreno.")
            @RequestParam(required = false) TipoTerreno tipoTerreno,

            @Parameter(description = "Filtrar pelo ID do Capítulo.")
            @RequestParam(required = false) Long capituloId,

            @PageableDefault(size = 20, sort = "dataInicio",
                             direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<MissaoResponse> pagina = buscarMissaoUseCase
            .listarComFiltros(status, tipoInimigo, tipoTerreno, capituloId, pageable);
        return ResponseEntity.ok(pagina);
    }
}
