package com.exemplo.agenda.controller;

import com.exemplo.agenda.entity.Agenda;
import com.exemplo.agenda.service.AgendaService;
import com.exemplo.agenda.service.ClienteService;
import com.exemplo.agenda.service.ServicoService;
import com.exemplo.agenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendamentos")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendaService.listarTodas());
        return "agendamentos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("servicos", servicoService.listarTodos());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "agendamentos/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Agenda agenda, Model model) {
        try {
            agendaService.salvar(agenda);
            return "redirect:/agendamentos";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("agenda", agenda);
            model.addAttribute("clientes", clienteService.listarTodos());
            model.addAttribute("servicos", servicoService.listarTodos());
            model.addAttribute("usuarios", usuarioRepository.findAll());
            return "agendamentos/form";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        agendaService.excluir(id);
        return "redirect:/agendamentos";
    }
}