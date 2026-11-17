package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Service
public class DispositivoService {
    
    @Autowired
    RepositoryDispositivo repositoryDispositivo;

    public List<Dispositivo> getAllDispositivos() {
        return repositoryDispositivo.findAll();
    }

    public Dispositivo getDispositivo(long id) {
        return repositoryDispositivo.getReferenceById(id);
    }

    public Dispositivo addDispositivo(Dispositivo dispositivo) {
        return repositoryDispositivo.saveAndFlush(dispositivo);
    }

    public void updateDispositivo(Dispositivo dispositivo) {
        Dispositivo d = repositoryDispositivo.getReferenceById(dispositivo.getId());
        d.setModelo(dispositivo.getModelo());
        d.setBateria(dispositivo.getBateria());
        repositoryDispositivo.saveAndFlush(d);
    }

    public void removeDispositivo(Dispositivo dispositivo) {
        repositoryDispositivo.delete(dispositivo);
    }

    public void removeDispositivoById(long id) {
        repositoryDispositivo.deleteById(id);
    }

    public List<Dispositivo> obtenerDispositivosPorUsuario(long usuarioId) {
        return repositoryDispositivo.findByTitularId(usuarioId);
    }
}
