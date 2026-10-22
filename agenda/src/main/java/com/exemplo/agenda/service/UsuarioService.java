package com.exemplo.agenda.service;

import com.exemplo.agenda.entity.Usuario;
import com.exemplo.agenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario salvar(Usuario usuario) {
        // RN08 - Tipo de acesso obrigatório
        if (usuario.getTipoAcesso() == null || usuario.getTipoAcesso().getId() == null) {
            throw new RuntimeException("O usuário deve possuir um Tipo de Acesso válido.");
        }

        // RN09 - Criptografia de senha com BCrypt
        if (usuario.getSenha() != null && !usuario.getSenha().startsWith("$2a$")) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
        }

        if (usuario.getDtCadastro() == null) {
            usuario.setDtCadastro(LocalDate.now());
        }

        return usuarioRepository.save(usuario);
    }
}