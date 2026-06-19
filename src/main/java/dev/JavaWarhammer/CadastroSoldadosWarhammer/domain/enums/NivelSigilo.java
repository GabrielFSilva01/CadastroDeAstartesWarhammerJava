package dev.JavaWarhammer.CadastroSoldadosWarhammer.domain.enums;

/**
 * Classificação de Sigilo Operacional — baseado na Ordo Inquisitorius.
 *
 * A ordem de declaração define o nível crescente de restrição de acesso.
 * O campo {@code clearanceMinimo} mapeia para o nível de Patente necessário
 * para visualizar a missão (integração futura com o Enum Patente).
 */
public enum NivelSigilo {

    VERDE   ("Informação Pública",   1, "Qualquer soldado autenticado pode visualizar."),
    AMARELO ("Uso Restrito",         3, "Requer patente de Sargento ou superior."),
    VERMELHO("Segredo Máximo",       6, "Requer patente de Capitão ou superior."),
    NEGRO   ("Clausura Absoluta",    9, "Acesso exclusivo do Primarca.");

    private final String descricao;
    private final int    clearanceMinimo; // Nível de Patente mínimo (campo 'nivel' do Enum Patente)
    private final String nota;

    NivelSigilo(String descricao, int clearanceMinimo, String nota) {
        this.descricao       = descricao;
        this.clearanceMinimo = clearanceMinimo;
        this.nota            = nota;
    }

    public String getDescricao()       { return descricao; }
    public int    getClearanceMinimo() { return clearanceMinimo; }
    public String getNota()            { return nota; }

    /** Retorna true se o nível de acesso fornecido satisfaz este sigilo. */
    public boolean permiteAcesso(int nivelPatente) {
        return nivelPatente >= this.clearanceMinimo;
    }
}
