package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAcessorio;
    private String nomeAcessorio;
    private String descricaoAcessorio;
    private String PropositoAcessorio;

    public String getNomeAcessorio() {
        return nomeAcessorio;
    }

    public void setNomeAcessorio(String nomeAcessorio) {
        this.nomeAcessorio = nomeAcessorio;
    }

    public int getIdAcessorio() {
        return idAcessorio;
    }

    public void setIdAcessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;
    }

    public String getDescricaoAcessorio() {
        return descricaoAcessorio;
    }

    public void setDescricaoAcessorio(String descricaoAcessorio) {
        this.descricaoAcessorio = descricaoAcessorio;
    }

    public String getPropositoAcessorio() {
        return PropositoAcessorio;
    }

    public void setPropositoAcessorio(String propositoAcessorio) {
        PropositoAcessorio = propositoAcessorio;
    }

}
