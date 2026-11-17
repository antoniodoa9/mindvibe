package com.uma.example.springuma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.uma.example.springuma.model.Usuario;
import com.uma.example.springuma.model.UsuarioService;

/**
 * @author GRUPO I de Ingenieria del Software
 */
@Controller
public class UsuarioViewController {

    @Autowired
    private UsuarioService usuarioService;
    
    /* --- Devuelve la vista inicial --- */
    @GetMapping("/")
    public String indexUsuarioView() {
        return "indexUsuario";
    }

    /* --- Devuelve el formulario para añadir un nuevo usuario --- */
    @GetMapping("/addUsuario")
    public String addUsuarioView(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "addUsuario";
    }

    /* --- Devuelve la lista de usuarios registrados --- */
    @GetMapping("/listUsuario")
    public String listUsuarioView(Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "listUsuario";
    }

    /* --- Guarda un nuevo usuario y redirige a la lista de usuarios --- */
    @PostMapping("/usuario")
    public String saveUsuario(Usuario usuario, BindingResult result, Model model) {
        try {
            usuarioService.addUsuario(usuario);
            model.addAttribute("create", true);
        } catch (Exception er) {
            model.addAttribute("create", false);
        }
        return listUsuarioView(model);
    }

    /* --- Devuelve el formulario para editar un usuario específico --- */
    @GetMapping("/editUsuario/{id}")
    public String editUsuarioView(@PathVariable("id") long id, Model model) {
        model.addAttribute("usuario", usuarioService.getUsuario(id));
        return "updateUsuario";
    }

    /* --- Actualiza un usuario existente y redirige a la lista --- */
    @PutMapping("/usuario")
    public String updateUsuario(Usuario usuario, Model model) {
        try {
            usuarioService.updateUsuario(usuario);
            model.addAttribute("update", true);
        } catch (Exception er) {
            er.printStackTrace();
            model.addAttribute("update", false);
        }
        return listUsuarioView(model);
    }

    /* --- Elimina un usuario por ID y redirige a la lista de usuarios --- */
    @DeleteMapping("/usuario/{id}")
    public String deleteUsuario(@PathVariable("id") long id, Model model) {
        try {
            usuarioService.removeUsuarioById(id);
            model.addAttribute("delete", true);
        } catch (Exception er) {
            model.addAttribute("delete", false);
        }
        return listUsuarioView(model);
    }
}
