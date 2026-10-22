package com.exemplo.agenda.service;

import com.exemplo.agenda.entity.Cliente;
import com.exemplo.agenda.repository.AgendaRepository;
import com.exemplo.agenda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        // RN07 - E-mail válido e não duplicado
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            throw new RuntimeException("Formato de e-mail inválido.");
        }

        if (cliente.getId() == null) {
            if (clienteRepository.existsByEmail(cliente.getEmail())) {
                throw new RuntimeException("Já existe um cliente cadastrado com este e-mail.");
            }
        } else {
            if (clienteRepository.existsByEmailAndIdNot(cliente.getEmail(), cliente.getId())) {
                throw new RuntimeException("Já existe outro cliente cadastrado com este e-mail.");
            }
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        // RN06 - Não excluir cliente com agendamento
        if (agendaRepository.existsByClienteId(id)) {
            throw new RuntimeException("Não é possível excluir o cliente pois ele possui agendamentos.");
        }
        clienteRepository.deleteById(id);
    }
}