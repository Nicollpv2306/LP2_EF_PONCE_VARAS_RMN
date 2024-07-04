package com.cibertec.entidad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entidad.Usuario;
import com.cibertec.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	

	
	@Override
	public Usuario añadirUser(Usuario user) {
		return repository.save(user);
	}

	@Override
	public boolean validarUser(Usuario user, HttpSession session) {
		Usuario usuarioEncontrado = repository.findByCorreo(user.getCorreo());
        if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(user.getPassword())) {
            
            return true;
        }
        return false;
	}

	@Override
	public Usuario buscarUserPorCorreo(String correo) {
		return repository.findByCorreo(correo);
	}

}
