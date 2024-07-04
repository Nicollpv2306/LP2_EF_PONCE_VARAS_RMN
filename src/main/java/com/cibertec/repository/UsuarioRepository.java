package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.entidad.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	 Usuario findByCorreo(String correo);
}
