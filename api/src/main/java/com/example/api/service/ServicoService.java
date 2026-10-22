package com.example.api.service;

import com.example.api.entity.Servico;
import com.example.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<Servico> listarTodos() {
        return repository.findAll();
    }

    public Optional<Servico> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Servico salvar(Servico servico) {
        if (servico.getDtCadastro() == null) {
            servico.setDtCadastro(LocalDate.now());
        }
        return repository.save(servico);
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }
}