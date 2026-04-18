package dev.JavaWarhammer.CadastroSoldadosWarhammer.service;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Capitulo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.repository.CapituloRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CapituloService {

    private final CapituloRepository capituloRepository;

    public CapituloService(CapituloRepository capituloRepository) {
        this.capituloRepository = capituloRepository;
    }

    public List<Capitulo> getAll() {
        return capituloRepository.findAll();
    }

    public Capitulo save(Capitulo capitulo) {
        return capituloRepository.save(capitulo);
    }

    public void deleteById(Long id) {
        capituloRepository.deleteById(id);
    }


    public Capitulo updateCapitulo(Long id, Capitulo capituloComNovosDados) {
        return capituloRepository.findById(id).map(capituloExistente -> {

            capituloExistente.setNome(capituloComNovosDados.getNome());
            capituloExistente.setDescricao(capituloComNovosDados.getDescricao());
            capituloExistente.setLocalidade(capituloComNovosDados.getLocalidade());
            capituloExistente.setQuantidadeMissoes(capituloComNovosDados.getQuantidadeMissoes());
            capituloExistente.setNumeroSoldados(capituloComNovosDados.getNumeroSoldados());

            return capituloRepository.save(capituloExistente);
        }).orElseThrow(() -> new RuntimeException("Capítulo não encontrado com o id: " + id));
    }
}