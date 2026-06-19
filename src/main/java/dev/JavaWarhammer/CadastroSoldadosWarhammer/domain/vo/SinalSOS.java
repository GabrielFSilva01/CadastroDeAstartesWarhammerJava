package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.vo;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.OrigemSOS;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * Value Object: Sinal de Socorro (SOS) da Guarda Imperial.
 *
 * Embutido diretamente na tabela tb_missoes via @Embeddable.
 * Não possui identidade própria — pertence exclusivamente à Missão.
 *
 * Uso dos factory methods para garantir estados válidos:
 *   SinalSOS.inativo()          → missão sem SOS
 *   SinalSOS.ativar(...)        → missão com SOS ativo
 */
@Embeddable
public class SinalSOS {

    @Column(name = "sos_ativo", nullable = false)
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "sos_origem", length = 30)
    private OrigemSOS origem;

    @Column(name = "sos_coordenadas", length = 150)
    private String coordenadasAlvo;

    @Column(name = "sos_timestamp")
    private LocalDateTime timestampRecebido;

    @Column(name = "sos_respondido", nullable = false)
    private boolean respondido;

    /** Construtor protegido — JPA requer construtor sem argumentos para @Embeddable. */
    protected SinalSOS() {
        this.ativo      = false;
        this.respondido = false;
    }

    // ── Factory Methods ──────────────────────────────────────────────

    /** Cria um SinalSOS inativo (estado padrão ao criar uma missão). */
    public static SinalSOS inativo() {
        return new SinalSOS();
    }

    /**
     * Cria um SinalSOS ativo com origem e coordenadas do alvo.
     *
     * @param origem       Quem enviou o sinal de socorro
     * @param coordenadas  Localização do sinal (ex: "Setor 7G, Colmeia Tertius")
     * @throws IllegalArgumentException se origem ou coordenadas forem nulos/vazios
     */
    public static SinalSOS ativar(OrigemSOS origem, String coordenadas) {
        if (origem == null) {
            throw new IllegalArgumentException("A origem do SOS não pode ser nula.");
        }
        if (coordenadas == null || coordenadas.isBlank()) {
            throw new IllegalArgumentException("As coordenadas do SOS não podem ser vazias.");
        }
        SinalSOS sos = new SinalSOS();
        sos.ativo              = true;
        sos.origem             = origem;
        sos.coordenadasAlvo    = coordenadas.trim();
        sos.timestampRecebido  = LocalDateTime.now();
        sos.respondido         = false;
        return sos;
    }

    // ── Comportamentos de Domínio ─────────────────────────────────────

    /**
     * Marca o SOS como respondido (unidades enviadas ao local).
     *
     * @throws IllegalStateException se o SOS não estiver ativo
     */
    public void marcarComoRespondido() {
        if (!this.ativo) {
            throw new IllegalStateException("Não é possível responder a um SOS inativo.");
        }
        this.respondido = true;
    }

    /** Retorna true apenas se o SOS foi ativado e ainda não foi respondido. */
    public boolean isAtivoPendente() {
        return this.ativo && !this.respondido;
    }

    // ── Getters (sem setters — Value Object é imutável após criação) ──

    public boolean      isAtivo()             { return ativo; }
    public OrigemSOS    getOrigem()            { return origem; }
    public String       getCoordenadasAlvo()  { return coordenadasAlvo; }
    public LocalDateTime getTimestampRecebido(){ return timestampRecebido; }
    public boolean      isRespondido()         { return respondido; }
}
