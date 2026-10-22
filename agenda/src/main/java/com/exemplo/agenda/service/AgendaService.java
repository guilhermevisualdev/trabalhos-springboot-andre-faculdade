package com.exemplo.agenda.service;

import com.exemplo.agenda.entity.Agenda;
import com.exemplo.agenda.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public List<Agenda> listarTodas() {
        return agendaRepository.findAll();
    }

    public Agenda salvar(Agenda agenda) {
        // RN04 - Cliente obrigatório
        if (agenda.getCliente() == null || agenda.getCliente().getId() == null) {
            throw new RuntimeException("É obrigatório selecionar um cliente.");
        }

        // RN01 - Agendamento futuro
        if (agenda.getData() == null || agenda.getData().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A data do agendamento não pode ser anterior à data/hora atual.");
        }

        // RN02 - Conflito de agendamento (mesmo usuário, mesma data e hora)
        if (agenda.getUsuario() != null && agenda.getUsuario().getId() != null) {
            boolean conflito = agendaRepository.existsByUsuarioIdAndData(
                    agenda.getUsuario().getId(), 
                    agenda.getData()
            );
            if (conflito) {
                throw new RuntimeException("O usuário selecionado já possui um agendamento nesta data e horário.");
            }
        }

        return agendaRepository.save(agenda);
    }

    public void excluir(Long id) {
        agendaRepository.deleteById(id);
    }
}