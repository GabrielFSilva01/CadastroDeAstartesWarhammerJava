package dev.JavaWarhammer.CadastroSoldadosWarhammer.model;

import jakarta.persistence.*;
import java.time.LocalDate; // Mais moderno e seguro que Calendar
import java.util.Objects;


@Entity
@Table(name = "tb_missoes")
public class Missoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusMissao status;

    public Missoes() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public StatusMissao getStatus() { return status; }
    public void setStatus(StatusMissao status) { this.status = status; }


    public boolean isConcluida() {
        return StatusMissao.CONCLUIDA.equals(this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Missoes missoes = (Missoes) o;
        return Objects.equals(id, missoes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public enum StatusMissao {
        PLANEJADA,
        EM_ANDAMENTO,
        CONCLUIDA,
        FRACASSADA,
        ABORTADA
    }
}