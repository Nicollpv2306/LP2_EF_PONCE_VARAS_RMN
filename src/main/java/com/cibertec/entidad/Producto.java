package com.cibertec.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="tb_producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "producto")
	private String nombreProducto;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "categoria")
	private String categoria;
}
