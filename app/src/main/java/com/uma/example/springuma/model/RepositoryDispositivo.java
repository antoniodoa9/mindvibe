package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GRUPO I de Ingenieria del Software
 */
public interface RepositoryDispositivo extends JpaRepository<Dispositivo, Long> {

    List<Dispositivo> findByTitularId(long titularId);
    
}
