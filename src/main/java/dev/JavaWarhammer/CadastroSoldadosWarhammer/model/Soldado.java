package dev.JavaWarhammer.CadastroSoldadosWarhammer.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_soldados")
public class Soldado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String descricao;

    private double altura; // Corrigido para camelCase


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capitulo_id")
    private Capitulo capitulo;

    public Soldado() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public Capitulo getCapitulo() { return capitulo; }
    public void setCapitulo(Capitulo capitulo) { this.capitulo = capitulo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soldado soldado = (Soldado) o;
        return Objects.equals(id, soldado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}