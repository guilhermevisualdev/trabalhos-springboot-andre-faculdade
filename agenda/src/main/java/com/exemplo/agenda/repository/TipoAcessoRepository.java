package com.exemplo.agenda.repository;

import com.exemplo.agenda.entity.TipoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAcessoRepository extends JpaRepository<TipoAcesso, Long> {
}