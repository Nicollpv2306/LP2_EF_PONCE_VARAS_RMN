package com.cibertec.entidad.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.entidad.Usuario;
import com.cibertec.entidad.services.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;

import com.cibertec.entidad.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	// ver login
	@GetMapping("/loginUsuario")
	public String verLoginUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";	
	}
	
	// iniciar sesion
	@PostMapping("/loginUsuario")
	public String login(Usuario user, Model model, HttpSession session) {
		boolean usuarioEncontrado = usuarioService.validarUser(user, session);
		if(usuarioEncontrado) {
			session.setAttribute("nombreUsuario", user.getNombre());
			//model.addAttribute("nombreUsu", user.getNombre());
			return "redirect:/producto/listado";
		}
		model.addAttribute("loginInvalido", "No existe el usuario");
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	
	// ver registrar usuario
	@GetMapping("/registrarUsuario")
	public String showRegistrarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registrarUsuario";	
	}
	
	// registrar usuario con los datos del form
	@PostMapping("/registrarUsuario")
	public String registrarUsuario(Usuario user, Model model) {
		usuarioService.añadirUser(user);
		return "redirect:/usuario/loginUsuario";
	}
	
	// cerrar sesión
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/usuario/loginUsuario";
	}
}
