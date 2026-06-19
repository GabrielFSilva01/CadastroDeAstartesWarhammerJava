package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Status de presença e condição de civis na zona de combate.
 *
 * Afeta as regras de engajamento da missão. Futuramente será usado pelo
 * motor de batalha para aplicar penalidades ou restrições de fogo.
 */
public enum StatusCivis {

    AUSENTE      ("Sem civis na zona de combate.",
                  false, "Combate sem restrições de engajamento."),

    PRESENTE     ("Civis presentes na área.",
                  true,  "Regras de engajamento ativas — minimizar baixas civis."),

    EM_RISCO     ("Civis sob ameaça direta.",
                  true,  "Prioridade de resgate. Objetivos secundários ativados."),

    EM_EVACUACAO ("Evacuação em andamento.",
                  true,  "Proteger corredores de fuga. Não bloquear rotas."),

    EVACUADOS    ("Civis a salvo.",
                  false, "Restrições levantadas. Combate sem limitações."),

    PERDIDOS     ("Situação de civis desconhecida.",
                  true,  "Risco de fogo amigo. Proceder com cautela máxima.");

    private final String  descricao;
    private final boolean exigeRestricaoEngajamento;
    private final String  diretrizTatica;

    StatusCivis(String descricao, boolean exigeRestricaoEngajamento, String diretrizTatica) {
        this.descricao                   = descricao;
        this.exigeRestricaoEngajamento   = exigeRestricaoEngajamento;
        this.diretrizTatica              = diretrizTatica;
    }

    public String  getDescricao()                    { return descricao; }
    public boolean isExigeRestricaoEngajamento()     { return exigeRestricaoEngajamento; }
    public String  getDiretrizTatica()               { return diretrizTatica; }

    /** Retorna true se há civis que ainda precisam de proteção ativa. */
    public boolean exigeProtecaoAtiva() {
        return this == EM_RISCO || this == EM_EVACUACAO || this == PERDIDOS;
    }
}
