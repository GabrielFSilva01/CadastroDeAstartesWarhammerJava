package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Origem de um Sinal de Socorro (SOS) recebido durante uma missão.
 * Separado do Value Object {@link dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.vo.SinalSOS}
 * para manter o princípio SRP e permitir uso independente.
 */
public enum OrigemSOS {

    GUARDA_IMPERIAL       ("Guarda Imperial",          "Forças regulares do Imperium"),
    ARBITES               ("Adeptus Arbites",           "Força policial imperial"),
    ADEPTUS_MECHANICUS    ("Adeptus Mechanicus",        "Culto da Máquina — Forge World"),
    POPULACAO_LOCAL       ("População Local",           "Civis ou milícia planetária"),
    DESCONHECIDO          ("Origem Desconhecida",       "Sinal sem identificação — possível armadilha");

    private final String nomeExibicao;
    private final String descricao;

    OrigemSOS(String nomeExibicao, String descricao) {
        this.nomeExibicao = nomeExibicao;
        this.descricao    = descricao;
    }

    public String getNomeExibicao() { return nomeExibicao; }
    public String getDescricao()    { return descricao; }

    /** Retorna true se a origem pode ser uma armadilha inimiga. */
    public boolean isPotencialmenteHostil() {
        return this == DESCONHECIDO;
    }
}
