package dev.JavaWarhammer.CadastroSoldadosWarhammer.repository;

import java.nio.channels.FileChannel;


import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Missoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface MissoesRepository extends JpaRepository<Missoes, Long> {

}
