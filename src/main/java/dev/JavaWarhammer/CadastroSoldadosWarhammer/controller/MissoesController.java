package dev.JavaWarhammer.CadastroSoldadosWarhammer.controller;



import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Missoes;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.service.MissoesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping
    public List<Missoes> getAll() {

        return missoesService.getAll();
    }

    @PostMapping
    public Missoes save(@RequestBody Missoes missoes) {

        return missoesService.save(missoes);
    }

    @PutMapping("/{id}")
    public Missoes update(@PathVariable Long id, @RequestBody Missoes missoes) {
        return missoesService.update(id, missoes);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        missoesService.delete(id);
    }
}