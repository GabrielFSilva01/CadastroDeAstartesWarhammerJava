package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Teatro de Operações — classifica o tipo de terreno da missão.
 *
 * Os flags {@code temCobertura} e {@code temHazardsAmbientais} serão usados
 * futuramente pelo motor de batalha (EscaramucaEngine) para modificar cálculos
 * de dano e salvaguarda no simulador tático.
 */
public enum TipoTerreno {

    URBANO       ("Colmeia Urbana",       true,  false,
                  "Corredores apertados, cobertura abundante. Favorece o defensor."),
    VOID         ("Combate Espacial",     false, false,
                  "Gravidade zero, sem cobertura. Movimentação e visibilidade maximizadas."),
    SELVA        ("Selva Primitiva",      true,  true,
                  "Vegetação densa + fauna hostil. Alto risco ambiental."),
    DESERTO      ("Deserto Árido",        false, true,
                  "Visibilidade máxima, calor extremo. Hazard de desidratação."),
    SUBTERRANEO  ("Catacombas",           true,  true,
                  "Labirinto subterrâneo. Risco de colapso e gás tóxico."),
    NAVAL        ("Abordagem Naval",      true,  false,
                  "Deck de nave espacial ou embarcação. Risco de descompressão."),
    FORTALEZA    ("Fortaleza Assediada",  true,  false,
                  "Muralhas e torres de defesa. Vantagem massiva para o defensor.");

    private final String  descricao;
    private final boolean temCobertura;
    private final boolean temHazardsAmbientais;
    private final String  notaTatica;

    TipoTerreno(String descricao, boolean temCobertura,
                boolean temHazardsAmbientais, String notaTatica) {
        this.descricao              = descricao;
        this.temCobertura           = temCobertura;
        this.temHazardsAmbientais   = temHazardsAmbientais;
        this.notaTatica             = notaTatica;
    }

    public String  getDescricao()             { return descricao; }
    public boolean isTemCobertura()           { return temCobertura; }
    public boolean isTemHazardsAmbientais()   { return temHazardsAmbientais; }
    public String  getNotaTatica()            { return notaTatica; }

    /** Retorna true se o terreno favorece os defensores (cobertura disponível). */
    public boolean isVantajosoParaDefensores() { return this.temCobertura; }
}
