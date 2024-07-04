package com.cibertec.entidad.services;

import com.cibertec.entidad.Usuario;

import jakarta.servlet.http.HttpSession;

public interface UsuarioService {
	public abstract Usuario añadirUser(Usuario user);
	public abstract boolean validarUser(Usuario user, HttpSession session);
	public abstract Usuario buscarUserPorCorreo(String correo);
}
