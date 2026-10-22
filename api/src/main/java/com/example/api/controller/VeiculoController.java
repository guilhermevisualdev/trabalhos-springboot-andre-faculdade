package com.example.api.controller;

import com.example.api.entity.Veiculo;
import com.example.api.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @GetMapping
    public List<Veiculo> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
        return service.buscarPorPlaca(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody Veiculo veiculo) {
        Veiculo salvo = service.salvar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable String placa, @RequestBody Veiculo veiculo) {
        if (!service.buscarPorPlaca(placa).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        veiculo.setPlaca(placa);
        Veiculo atualizado = service.salvar(veiculo);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> excluir(@PathVariable String placa) {
        if (!service.buscarPorPlaca(placa).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(placa);
        return ResponseEntity.noContent().build();
    }
}