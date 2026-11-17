package com.uma.example.springuma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.uma.example.springuma.model.Dispositivo;
import com.uma.example.springuma.model.Usuario;
import com.uma.example.springuma.model.DispositivoService;
import com.uma.example.springuma.model.UsuarioService;

import java.util.List;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Controller
public class DispositivoViewController {

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista los dispositivos del usuario actual.
     * @param usuarioId ID del usuario actual.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista de la lista de dispositivos.
     */
    @GetMapping("/listUsuarioDispositivos/{usuarioId}")
public String listUsuarioDispositivosView(@PathVariable("usuarioId") Long usuarioId, Model model) {
    Usuario usuario = usuarioService.getUsuario(usuarioId);
    if (usuario == null) {
        model.addAttribute("error", "Usuario no encontrado.");
        return "error";
    }
    List<Dispositivo> dispositivos = dispositivoService.obtenerDispositivosPorUsuario(usuarioId);

    // Forzar la inicialización de la relación `titular` en cada dispositivo
    dispositivos.forEach(dispositivo -> {
        if (dispositivo.getTitular() != null) {
            dispositivo.getTitular().getNombre();
        }
    });

    model.addAttribute("dispositivos", dispositivos);
    model.addAttribute("usuario", usuario);
    return "listUsuarioDispositivos";
}



    /**
     * Muestra el formulario para añadir un nuevo dispositivo asociado al usuario actual.
     * @param usuarioId ID del usuario actual.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario para añadir un dispositivo.
     */
    @GetMapping("/addDispositivo/{usuarioId}")
    public String addDispositivoView(@PathVariable("usuarioId") Long usuarioId, Model model) {
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "error";
        }
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTitular(usuario);
        model.addAttribute("dispositivo", dispositivo);
        return "addUsuarioDispositivo";
    }

    /**
     * Guarda un nuevo dispositivo asociado al usuario actual.
     * @param dispositivo Dispositivo a guardar.
     * @param result Resultado de la validación del formulario.
     * @param model Modelo para pasar datos a la vista.
     * @return Redirige a la lista de dispositivos del usuario actual.
     */
    @PostMapping("/dispositivo")
    public String saveDispositivo(@ModelAttribute Dispositivo dispositivo, BindingResult result, Model model) {
        try {
            dispositivoService.addDispositivo(dispositivo);
            model.addAttribute("create", true);
        } catch (Exception er) {
            model.addAttribute("create", false);
            er.printStackTrace();
        }
        return "redirect:/listUsuarioDispositivos/" + dispositivo.getTitular().getId();
    }

    /**
     * Muestra el formulario para editar un dispositivo existente.
     * @param id ID del dispositivo a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario para editar un dispositivo.
     */
    @GetMapping("/editDispositivo/{id}")
    public String editDispositivoView(@PathVariable("id") Long id, Model model) {
        Dispositivo dispositivo = dispositivoService.getDispositivo(id);
        if (dispositivo == null) {
            model.addAttribute("error", "Dispositivo no encontrado.");
            return "error";
        }
        model.addAttribute("dispositivo", dispositivo);
        return "editUsuarioDispositivo";
    }

    /**
     * Actualiza un dispositivo existente.
     * @param dispositivo Dispositivo con los cambios realizados.
     * @param model Modelo para pasar datos a la vista.
     * @return Redirige a la lista de dispositivos del usuario actual.
     */
    @PutMapping("/dispositivo")
    public String updateDispositivo(@ModelAttribute Dispositivo dispositivo, Model model) {
        try {
            dispositivoService.updateDispositivo(dispositivo);
            model.addAttribute("update", true);
        } catch (Exception er) {
            model.addAttribute("update", false);
            er.printStackTrace();
        }
        return "redirect:/listUsuarioDispositivos/" + dispositivo.getTitular().getId();
    }

    /**
     * Elimina un dispositivo por su ID.
     * @param id ID del dispositivo a eliminar.
     * @param model Modelo para pasar datos a la vista.
     * @return Redirige a la lista de dispositivos del usuario actual.
     */
    @DeleteMapping("/dispositivo/{id}")
    public String deleteDispositivo(@PathVariable("id") Long id, Model model) {
        Dispositivo dispositivo = dispositivoService.getDispositivo(id);
        try {
            dispositivoService.removeDispositivoById(id);
            model.addAttribute("delete", true);
        } catch (Exception er) {
            model.addAttribute("delete", false);
            er.printStackTrace();
        }
        return "redirect:/listUsuarioDispositivos/" + dispositivo.getTitular().getId();
    }
}
