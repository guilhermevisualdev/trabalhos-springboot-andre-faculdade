package com.example.api.service;

import com.example.api.entity.Veiculo;
import com.example.api.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return repository.findById(placa);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void excluir(String placa) {
        repository.deleteById(placa);
    }
}