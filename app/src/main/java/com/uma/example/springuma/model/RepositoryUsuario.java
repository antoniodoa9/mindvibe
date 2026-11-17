package com.uma.example.springuma.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GRUPO I de Ingenieria del Software
 */
public interface RepositoryUsuario extends JpaRepository<Usuario, Long> {
    
}
