package dev.JavaWarhammer.CadastroSoldadosWarhammer.repository;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Capitulo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CapituloRepository extends JpaRepository<Capitulo, Long> {
    List<Capitulo> findByLocalidade(String localidade);

    // Busca capítulos que tenham menos de X soldados (útil para reforços)
    List<Capitulo> findByNumeroSoldadosLessThan(int limite);
}

