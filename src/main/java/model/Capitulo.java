package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Capitulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCapitulo;
    private String NomeCapitulo;
    private String Descricao;
    private String LocalidadeCapitulo;
    private int QtdeMissoesDoCapitulo;
    private int NumeroDeSoldadosNoCapitulo;

    public int getIdCapitulo() {
        return IdCapitulo;
    }

    public void setIdCapitulo(int idCapitulo) {
        IdCapitulo = idCapitulo;
    }

    public String getNomeCapitulo() {
        return NomeCapitulo;
    }

    public void setNomeCapitulo(String nomeCapitulo) {
        NomeCapitulo = nomeCapitulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getLocalidadeCapitulo() {
        return LocalidadeCapitulo;
    }

    public void setLocalidadeCapitulo(String localidadeCapitulo) {
        LocalidadeCapitulo = localidadeCapitulo;
    }

    public int getQtdeMissoesDoCapitulo() {
        return QtdeMissoesDoCapitulo;
    }

    public void setQtdeMissoesDoCapitulo(int qtdeMissoesDoCapitulo) {
        QtdeMissoesDoCapitulo = qtdeMissoesDoCapitulo;
    }

    public int getNumeroDeSoldadosNoCapitulo() {
        return NumeroDeSoldadosNoCapitulo;
    }

    public void setNumeroDeSoldadosNoCapitulo(int numeroDeSoldadosNoCapitulo) {
        NumeroDeSoldadosNoCapitulo = numeroDeSoldadosNoCapitulo;
    }
}
