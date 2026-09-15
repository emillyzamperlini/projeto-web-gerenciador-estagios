package com.emilly.gerenciadorestagios.repository;

import com.emilly.gerenciadorestagios.model.Estagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstagioRepository extends JpaRepository<Estagio, Long> {
    List<Estagio> findByEmpresa(String empresa);
    List<Estagio> findAllByOrderByDataInicioAsc();
    List<Estagio> findAllByOrderByDataInicioDesc();
}


