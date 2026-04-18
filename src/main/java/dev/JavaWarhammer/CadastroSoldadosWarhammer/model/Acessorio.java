package dev.JavaWarhammer.CadastroSoldadosWarhammer.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_acessorios")
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private String proposito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soldado_id")
    private Soldado portador;

    public Acessorio() {}

    // Getters e Setters simplificados
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }

    public Soldado getPortador() { return portador; }
    public void setPortador(Soldado portador) { this.portador = portador; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acessorio acessorio = (Acessorio) o;
        return Objects.equals(id, acessorio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}