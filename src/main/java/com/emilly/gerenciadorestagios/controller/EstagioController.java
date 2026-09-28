package com.emilly.gerenciadorestagios.controller;

import org.springframework.ui.Model;
import com.emilly.gerenciadorestagios.model.Estagio;
import com.emilly.gerenciadorestagios.service.EstagioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EstagioController {
    private final EstagioService service;

    public EstagioController(EstagioService service) {
        this.service = service;
    }
    @GetMapping("/estagios")
    public String listar(Model model) {
        List<Estagio> estagios = service.listarTodos();
        model.addAttribute("estagios", estagios);
        return "estagios";
    }
    @GetMapping("/estagios/novo")
    public String novoEstagio(Model model) {
        model.addAttribute("estagio", new Estagio());
        return "formulario";
    }
    @PostMapping("/estagios")
    public String salvar(Estagio estagio, RedirectAttributes redirectAttributes) {
        service.salvar(estagio);
        redirectAttributes.addFlashAttribute("sucesso", "Estágio cadastrado com sucesso!");
        return "redirect:/estagios";
    }
    @GetMapping("/estagios/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Estagio estagio = service.buscarPorId(id).orElse(null);
        if (estagio == null) {
            redirectAttributes.addFlashAttribute("erro", "Estágio não encontrado.");
            return "redirect:/estagios";
        }
        model.addAttribute("estagio", estagio);
        return "formulario";
    }
    @PostMapping("/estagios/editar")
    public String atualizar(Estagio estagio, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("sucesso", "Estágio atualizado com sucesso!");
        service.atualizar(estagio);
        return "redirect:/estagios";
    }
    @GetMapping("/estagios/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (service.buscarPorId(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Estágio não encontrado.");
            return "redirect:/estagios";
        }
        service.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Estágio excluído com sucesso!");
        return "redirect:/estagios";
    }

    @GetMapping("/estagios/pesquisar")
    public String pesquisar(@RequestParam String empresa, Model model, RedirectAttributes redirectAttributes) {
        List<Estagio> estagios = service.pesquisarPorEmpresa(empresa);
        if (estagios.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Nenhum estágio encontrado para essa empresa.");
            return "redirect:/estagios";
        }
        model.addAttribute("estagios", estagios);
        return "estagios";
    }
    @GetMapping("/estagios/ordenar/crescente")
    public String ordenarCrescente(Model model) {
        List<Estagio> estagios = service.listarPorDataCrescente();
        model.addAttribute("estagios", estagios);
        return "estagios";
    }

    @GetMapping("/estagios/ordenar/decrescente")
    public String ordenarDecrescente(Model model) {
        List<Estagio> estagios = service.listarPorDataDecrescente();
        model.addAttribute("estagios", estagios);
        return "estagios";
    }
}
