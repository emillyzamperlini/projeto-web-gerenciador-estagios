package com.emilly.gerenciadorestagios.controller;

import org.springframework.ui.Model;
import com.emilly.gerenciadorestagios.model.Estagio;
import com.emilly.gerenciadorestagios.service.EstagioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String salvar(Estagio estagio) {
        service.salvar(estagio);
        return "redirect:/estagios";
    }
    @GetMapping("/estagios/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Estagio estagio = service.buscarPorId(id).orElseThrow();
        model.addAttribute("estagio", estagio);
        return "formulario";
    }
    @PostMapping("/estagios/editar")
    public String atualizar(Estagio estagio) {
        service.atualizar(estagio);
        return "redirect:/estagios";
    }
    @GetMapping("/estagios/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/estagios";
    }
    @GetMapping("/estagios/pesquisar")
    public String pesquisar(@RequestParam String empresa, Model model) {
        List<Estagio> estagios = service.pesquisarPorEmpresa(empresa);
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
