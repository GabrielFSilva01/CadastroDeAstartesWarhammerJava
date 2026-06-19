package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Facções inimigas conhecidas pelo Imperium.
 *
 * Cada constante carrega metadados de lore para uso no simulador tático
 * e nos relatórios narrativos enviados à API de IA.
 */
public enum TipoInimigo {

    // ── Forças do Caos ────────────────────────────────────────────────
    LEGIAO_DO_CAOS  ("Legião do Caos",       "CAOS",    "Traidores das Legiões Originais"),
    DEMONIO         ("Demônio",              "CAOS",    "Entidade do Warp materializada"),
    CULTISTA        ("Cultista do Caos",     "CAOS",    "Humanos corrompidos pelo Ruinous Powers"),

    // ── Xenos ─────────────────────────────────────────────────────────
    TIRANIDA        ("Tiranídeo",            "XENOS",   "Enxame Devorador de Mundos — Hive Mind"),
    ORK             ("Ork",                  "XENOS",   "WAAAGH! — Fúria verde incontrolável"),
    ELDAR           ("Eldar",                "XENOS",   "Alienígena Ancião — Craftworld"),
    ELDAR_SOMBRIO   ("Eldar Sombrio",        "XENOS",   "Piratas do Comoros — Commorragh"),
    NECRON          ("Necron",               "XENOS",   "Androide imortal — Despertar das Tumbas"),
    TAU             ("Tau",                  "XENOS",   "Expansionismo tecnológico — Pelo Bem Maior"),

    // ── Heresia Interna ────────────────────────────────────────────────
    HEREGE          ("Herege",               "INTERNO", "Humano corrompido pela heresia"),
    GENESTEALER     ("Culto Genestealer",    "INTERNO", "Infiltração Tiranídea disfarçada de culto");

    private final String nomeExibicao;
    private final String categoria;   // CAOS | XENOS | INTERNO — para filtros da API
    private final String descricaoLore;

    TipoInimigo(String nomeExibicao, String categoria, String descricaoLore) {
        this.nomeExibicao   = nomeExibicao;
        this.categoria      = categoria;
        this.descricaoLore  = descricaoLore;
    }

    public String getNomeExibicao()  { return nomeExibicao; }
    public String getCategoria()     { return categoria; }
    public String getDescricaoLore() { return descricaoLore; }

    public boolean isXenos()    { return "XENOS".equals(this.categoria); }
    public boolean isCaos()     { return "CAOS".equals(this.categoria); }
    public boolean isInterno()  { return "INTERNO".equals(this.categoria); }
}
