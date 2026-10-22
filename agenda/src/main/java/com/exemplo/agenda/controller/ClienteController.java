package com.exemplo.agenda.controller;

import com.exemplo.agenda.entity.Cliente;
import com.exemplo.agenda.entity.Endereco;
import com.exemplo.agenda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(new Endereco()); // Instancia o endereço junto para o formulário
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Cliente cliente, Model model) {
        try {
            clienteService.salvar(cliente);
            return "redirect:/clientes";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("cliente", cliente);
            return "clientes/form";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            clienteService.excluir(id);
            return "redirect:/clientes";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("clientes", clienteService.listarTodos());
            return "clientes/lista";
        }
    }
}