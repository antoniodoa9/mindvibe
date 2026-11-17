package com.uma.example.springuma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // para ignorar el serializador al devolver un objeto dispositivo
public class Dispositivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String modelo;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    private String bateria;

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    @ManyToOne
    @JoinColumn(name = "titular_id", nullable = false)
    private Usuario titular;

    public Usuario getTitular() {
        return titular;
    }

    public void setTitular(Usuario titular) {
        this.titular = titular;
    }
}
