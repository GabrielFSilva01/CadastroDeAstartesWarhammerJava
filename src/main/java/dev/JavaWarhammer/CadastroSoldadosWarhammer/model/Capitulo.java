package dev.JavaWarhammer.CadastroSoldadosWarhammer.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidade JPA de Capítulo Space Marine.
 *
 * NOTA ARQUITETURAL: Esta classe será migrada para
 * infrastructure/persistence/entity/ na Fase 3, junto com a modelagem completa
 * do Primarca e da hierarquia de Patentes.
 *
 * CAMPOS REMOVIDOS (dead data — Fase 1 Code Review):
 *   - quantidadeMissoes: derivável de MissaoRepository.findByCapituloId().size()
 *   - numeroSoldados:    derivável de SoldadoRepository.countByCapituloId()
 */
@Entity
@Table(name = "tb_capitulos")
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(length = 200)
    private String localidade;

    public Capitulo() {}

    public Long   getId()        { return id; }
    public void   setId(Long id) { this.id = id; }

    public String getNome()               { return nome; }
    public void   setNome(String nome)    { this.nome = nome; }

    public String getDescricao()                    { return descricao; }
    public void   setDescricao(String descricao)    { this.descricao = descricao; }

    public String getLocalidade()                   { return localidade; }
    public void   setLocalidade(String localidade)  { this.localidade = localidade; }

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