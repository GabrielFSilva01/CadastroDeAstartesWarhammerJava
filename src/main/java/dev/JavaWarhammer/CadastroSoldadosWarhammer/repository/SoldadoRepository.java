package dev.JavaWarhammer.CadastroSoldadosWarhammer.repository;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Soldado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SoldadoRepository extends JpaRepository<Soldado, Long> {

    // CORRIGIDO: Removido o "Soldado" do meio do nome do método
    List<Soldado> findByNomeContainingIgnoreCase(String nome);

    // Este aqui está correto, pois o atributo no Model é 'altura'
    List<Soldado> findByAlturaGreaterThan(double altura);
}