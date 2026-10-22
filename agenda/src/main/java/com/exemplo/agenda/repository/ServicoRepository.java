package com.exemplo.agenda.repository;

import com.exemplo.agenda.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}