package dev.JavaWarhammer.CadastroSoldadosWarhammer.controller;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Capitulo;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.service.CapituloService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Capitulo")
public class CapituloController {

    private final CapituloService capituloService;

    public CapituloController(CapituloService capituloService) {
        this.capituloService = capituloService;
    }

    @GetMapping
    public List<Capitulo> findAll() {
        return capituloService.getAll();
    }

    @PostMapping
    public Capitulo create(@RequestBody Capitulo capitulo) {
        return capituloService.save(capitulo);
    }

    @PutMapping("/{id}")
    public Capitulo update(@PathVariable Long id, @RequestBody Capitulo capitulo) {

        return capituloService.updateCapitulo(id, capitulo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        capituloService.deleteById(id);
    }
}