package com.emilly.gerenciadorestagios.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estagio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empresa;
    private String cargo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;

    public Estagio(){
    }
    public String getEmpresa() {
        return empresa;
    }
    public Long getId() {
        return id;
    }
    public String getCargo() {
        return cargo;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public String getStatus() {
        return status;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
