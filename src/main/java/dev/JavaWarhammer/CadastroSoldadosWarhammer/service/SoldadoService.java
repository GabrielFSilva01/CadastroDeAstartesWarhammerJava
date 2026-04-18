package dev.JavaWarhammer.CadastroSoldadosWarhammer.service;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.repository.SoldadoRepository;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Soldado;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SoldadoService {

    private final SoldadoRepository soldadoRepository;

    public SoldadoService(SoldadoRepository soldadoRepository) {
        this.soldadoRepository = soldadoRepository;
    }

    public List<Soldado> getAll() {
        return soldadoRepository.findAll();
    }

    public Soldado save(Soldado soldado) {
        return soldadoRepository.save(soldado);
    }

    public Soldado update(Long id, Soldado soldadoComDadosNovos) {
        return soldadoRepository.findById(id).map(soldadoNoBanco -> {

            soldadoNoBanco.setNome(soldadoComDadosNovos.getNome());
            soldadoNoBanco.setDescricao(soldadoComDadosNovos.getDescricao());
            soldadoNoBanco.setAltura(soldadoComDadosNovos.getAltura());
            soldadoNoBanco.setCapitulo(soldadoComDadosNovos.getCapitulo());


            return soldadoRepository.save(soldadoNoBanco);
        }).orElseThrow(() -> new RuntimeException("Soldado não encontrado!"));
    }


    public void delete(Long id) {
        if (!soldadoRepository.existsById(id)) {
            throw new RuntimeException("Nenhum soldado encontrado com o ID: " + id);
        }
        soldadoRepository.deleteById(id);
    }
}