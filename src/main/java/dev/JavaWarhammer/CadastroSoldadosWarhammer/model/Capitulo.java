package dev.JavaWarhammer.CadastroSoldadosWarhammer.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Capitulos_table ")
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String descricao;

    private String localidade;

    private int quantidadeMissoes;

    private int numeroSoldados;


    public Capitulo() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }

    public int getQuantidadeMissoes() { return quantidadeMissoes; }
    public void setQuantidadeMissoes(int quantidadeMissoes) { this.quantidadeMissoes = quantidadeMissoes; }

    public int getNumeroSoldados() { return numeroSoldados; }
    public void setNumeroSoldados(int numeroSoldados) { this.numeroSoldados = numeroSoldados; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capitulo capitulo = (Capitulo) o;
        return Objects.equals(id, capitulo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}