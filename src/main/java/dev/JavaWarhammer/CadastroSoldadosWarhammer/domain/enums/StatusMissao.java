package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Ciclo de vida de uma Missão Operacional.
 *
 * Movido de dentro de Missoes.java (fix CR-01 — violação de SRP).
 * Estados terminais: CONCLUIDA, FRACASSADA, ABORTADA.
 */
public enum StatusMissao {

    PLANEJADA     ("Missão planejada, aguardando ordem de execução."),
    EM_ANDAMENTO  ("Operação em curso."),
    CONCLUIDA     ("Missão cumprida com sucesso. O Imperador sorri."),
    FRACASSADA    ("Missão fracassada. Que o sangue dos mártires não seja em vão."),
    ABORTADA      ("Operação abortada por ordem superior ou perda de contato.");

    private final String descricao;

    StatusMissao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() { return descricao; }

    /** Retorna true para qualquer estado que encerra a missão definitivamente. */
    public boolean isEstadoTerminal() {
        return this == CONCLUIDA || this == FRACASSADA || this == ABORTADA;
    }
}
