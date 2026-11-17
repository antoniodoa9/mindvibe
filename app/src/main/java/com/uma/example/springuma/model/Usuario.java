package com.uma.example.springuma.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // para ignorar el serializador al devolver un objeto usuario
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(unique = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "titular", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Dispositivo> dispositivos = new ArrayList<>();

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public Usuario() {

    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

}
