package dev.JavaWarhammer.CadastroSoldadosWarhammer.service;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Missoes;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.repository.MissoesRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MissoesService {

    private final MissoesRepository missoesRepository;

    // Construtor para Injeção de Dependência
    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // REMOVIDO o 'static' para poder acessar o missoesRepository
    public List<Missoes> getAll() {
        return missoesRepository.findAll();
    }

    public Missoes save(Missoes missoes) {
        return missoesRepository.save(missoes);
    }

    public Missoes update(Long id, Missoes missoesAtualizadas) {
        return missoesRepository.findById(id).map(missaoComDadosNovos -> {

            missaoComDadosNovos.setNome(missoesAtualizadas.getNome());
            missaoComDadosNovos.setDescricao(missoesAtualizadas.getDescricao());
            missaoComDadosNovos.setData(missoesAtualizadas.getData());
            missaoComDadosNovos.setStatus(missoesAtualizadas.getStatus());

            return missoesRepository.save(missaoComDadosNovos);
        }).orElseThrow(() -> new RuntimeException("Missão não encontrada com o ID: " + id));
    }

    public void delete(Long id) {
        if (!missoesRepository.existsById(id)) {
            throw new RuntimeException("Nenhuma missão encontrada com o ID: " + id);
        }
        missoesRepository.deleteById(id);
    }
}