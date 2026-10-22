package com.exemplo.agenda.service;

import com.exemplo.agenda.entity.Servico;
import com.exemplo.agenda.repository.AgendaRepository;
import com.exemplo.agenda.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico salvar(Servico servico) {
        // RN03 - Valor do serviço > 0
        if (servico.getValor() == null || servico.getValor() <= 0) {
            throw new RuntimeException("O valor do serviço deve ser maior que zero.");
        }
        return servicoRepository.save(servico);
    }

    public void excluir(Long id) {
        // RN05 - Não excluir serviço com agendamento
        if (agendaRepository.existsByServicoId(id)) {
            throw new RuntimeException("Não é possível excluir o serviço pois ele possui agendamentos vinculados.");
        }
        servicoRepository.deleteById(id);
    }
}