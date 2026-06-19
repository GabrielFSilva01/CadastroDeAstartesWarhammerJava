package dev.JavaWarhammer.CadastroSoldadosWarhammer.infrastructure.persistence.entity;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums.*;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.vo.SinalSOS;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Capitulo;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade JPA da Missão — camada de infraestrutura/persistência.
 *
 * Substitui completamente a entidade legada {@code Missoes} (removida na Fase 1).
 * Contém todos os atributos táticos: Sigilo, Inimigo, Terreno, Civis e SOS.
 *
 * Relacionamento com Capítulo: obrigatório (nullable = false).
 * Auditoria: {@code criadoEm} preenchido automaticamente via @PrePersist.
 */
@Entity
@Table(name = "tb_missoes", indexes = {
    @Index(name = "idx_missao_status",        columnList = "status_missao"),
    @Index(name = "idx_missao_tipo_inimigo",  columnList = "tipo_inimigo"),
    @Index(name = "idx_missao_capitulo",      columnList = "capitulo_id")
})
public class MissaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Identificação ──────────────────────────────────────────────────
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    // ── Classificação Operacional ──────────────────────────────────────
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "nivel_sigilo", length = 15)
    private NivelSigilo nivelSigilo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_inimigo", length = 25)
    private TipoInimigo tipoInimigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_terreno", length = 20)
    private TipoTerreno tipoTerreno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_civis", length = 20)
    private StatusCivis statusCivis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_missao", length = 20)
    private StatusMissao statusMissao;

    // ── SOS: Value Object embutido ─────────────────────────────────────
    @Embedded
    private SinalSOS sinalSOS;

    // ── Temporal ───────────────────────────────────────────────────────
    @Column(nullable = false, name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao; // null = missão em aberto

    // ── Relação com Capítulo (obrigatória) ─────────────────────────────
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "capitulo_id", nullable = false)
    private Capitulo capitulo;

    // ── Auditoria ──────────────────────────────────────────────────────
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public MissaoEntity() {}

    // ── Hooks JPA ─────────────────────────────────────────────────────

    @PrePersist
    private void prePersist() {
        this.criadoEm = LocalDateTime.now();
        if (this.statusMissao == null) this.statusMissao = StatusMissao.PLANEJADA;
        if (this.statusCivis  == null) this.statusCivis  = StatusCivis.AUSENTE;
        if (this.sinalSOS     == null) this.sinalSOS     = SinalSOS.inativo();
    }

    // ── Comportamentos de Domínio ──────────────────────────────────────

    /** Retorna true se a missão está em um estado terminal (encerrada). */
    public boolean isConcluida() {
        return this.statusMissao != null && this.statusMissao.isEstadoTerminal();
    }

    /** Retorna true se há um SOS ativo e ainda não respondido. */
    public boolean temSosAtivoPendente() {
        return this.sinalSOS != null && this.sinalSOS.isAtivoPendente();
    }

    // ── Getters ───────────────────────────────────────────────────────

    public Long            getId()           { return id; }
    public String          getNome()         { return nome; }
    public String          getDescricao()    { return descricao; }
    public NivelSigilo     getNivelSigilo()  { return nivelSigilo; }
    public TipoInimigo     getTipoInimigo()  { return tipoInimigo; }
    public TipoTerreno     getTipoTerreno()  { return tipoTerreno; }
    public StatusCivis     getStatusCivis()  { return statusCivis; }
    public StatusMissao    getStatusMissao() { return statusMissao; }
    public SinalSOS        getSinalSOS()     { return sinalSOS; }
    public LocalDate       getDataInicio()   { return dataInicio; }
    public LocalDate       getDataConclusao(){ return dataConclusao; }
    public Capitulo        getCapitulo()     { return capitulo; }
    public LocalDateTime   getCriadoEm()    { return criadoEm; }

    // ── Setters (apenas campos mutáveis pós-criação) ──────────────────

    public void setNome(String nome)                    { this.nome = nome; }
    public void setDescricao(String descricao)          { this.descricao = descricao; }
    public void setNivelSigilo(NivelSigilo nivelSigilo) { this.nivelSigilo = nivelSigilo; }
    public void setTipoInimigo(TipoInimigo tipoInimigo) { this.tipoInimigo = tipoInimigo; }
    public void setTipoTerreno(TipoTerreno tipoTerreno) { this.tipoTerreno = tipoTerreno; }
    public void setStatusCivis(StatusCivis statusCivis) { this.statusCivis = statusCivis; }
    public void setStatusMissao(StatusMissao statusMissao){ this.statusMissao = statusMissao; }
    public void setSinalSOS(SinalSOS sinalSOS)          { this.sinalSOS = sinalSOS; }
    public void setDataInicio(LocalDate dataInicio)     { this.dataInicio = dataInicio; }
    public void setDataConclusao(LocalDate data)        { this.dataConclusao = data; }
    public void setCapitulo(Capitulo capitulo)          { this.capitulo = capitulo; }
}
