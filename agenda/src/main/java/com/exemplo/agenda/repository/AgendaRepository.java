package com.exemplo.agenda.repository;

import com.exemplo.agenda.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    boolean existsByServicoId(Long servicoId);
    boolean existsByClienteId(Long clienteId);
    boolean existsByUsuarioIdAndData(Long usuarioId, LocalDateTime data);
}