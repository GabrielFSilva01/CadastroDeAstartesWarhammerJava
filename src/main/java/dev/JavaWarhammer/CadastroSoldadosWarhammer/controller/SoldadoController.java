package dev.JavaWarhammer.CadastroSoldadosWarhammer.controller;

import dev.JavaWarhammer.CadastroSoldadosWarhammer.model.Soldado;
import dev.JavaWarhammer.CadastroSoldadosWarhammer.service.SoldadoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/soldados")
public class SoldadoController {

    private final SoldadoService soldadoService;

    public SoldadoController(SoldadoService soldadoService) {
        this.soldadoService = soldadoService;
    }

    @GetMapping
    public List<Soldado> getAll() {
        return soldadoService.getAll();
    }

    @PostMapping
    public Soldado create(@RequestBody Soldado soldado) {
        return soldadoService.save(soldado);
    }


    @PutMapping("/{id}")
    public Soldado update(@PathVariable Long id, @RequestBody Soldado soldado) {

        return soldadoService.update(id, soldado);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        soldadoService.delete(id);
    }
}