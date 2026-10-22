package com.exemplo.agenda.controller;

import com.exemplo.agenda.entity.Servico;
import com.exemplo.agenda.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicos", servicoService.listarTodos());
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("servico", new Servico());
        return "servicos/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Servico servico, Model model) {
        try {
            servicoService.salvar(servico);
            return "redirect:/servicos";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("servico", servico);
            return "servicos/form";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            servicoService.excluir(id);
            return "redirect:/servicos";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("servicos", servicoService.listarTodos());
            return "servicos/lista";
        }
    }
}