package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Service
public class UsuarioService {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public List<Usuario> getAllUsuarios() {
        return repositoryUsuario.findAll();
    }

    public Usuario getUsuario(long id) {
        return repositoryUsuario.getReferenceById(id);
    }
    
    public Usuario addUsuario(Usuario usuario) {
        return repositoryUsuario.saveAndFlush(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        Usuario u = repositoryUsuario.getReferenceById(usuario.getId());
        u.setNombre(usuario.getNombre());
        u.setEmail(usuario.getEmail());
        repositoryUsuario.saveAndFlush(u);
    }

    public void removeUsuario(Usuario usuario) {
        repositoryUsuario.delete(usuario);
    }

    public void removeUsuarioById(long id) {
        repositoryUsuario.deleteById(id);
    }
}
