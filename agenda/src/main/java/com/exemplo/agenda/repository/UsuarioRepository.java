package com.exemplo.agenda.repository;

import com.exemplo.agenda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}