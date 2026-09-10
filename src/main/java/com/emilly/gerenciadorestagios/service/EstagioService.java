package com.emilly.gerenciadorestagios.service;
import com.emilly.gerenciadorestagios.model.Estagio;
import com.emilly.gerenciadorestagios.repository.EstagioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstagioService {
    private final EstagioRepository repository;

    public EstagioService(EstagioRepository repository) {
        this.repository = repository;
    }
    public List<Estagio> listarTodos() {
        return repository.findAll();
    }
    public Estagio salvar(Estagio estagio) {
        return repository.save(estagio);
    }
    public Optional<Estagio> buscarPorId(Long id) {
        return repository.findById(id);
    }
    public Estagio atualizar(Estagio estagio) {
        return repository.save(estagio);
    }
    public void excluir(Long id) {
        repository.deleteById(id);
    }
    public List<Estagio> pesquisarPorEmpresa(String empresa) {
        return repository.findByEmpresa(empresa);
    }
}
