package com.cibertec.entidad;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@Column(name = "correoUsu")
	private String correo;
	
	@Column(name = "nombreUsu")
	private String nombre;
	
	@Column(name = "apellidoUsu")
	private String apellido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fechNacUsu")
	private Date fchaNacimiento;
	
	@Column(name = "clavUsu")
	private String password;
	
}
