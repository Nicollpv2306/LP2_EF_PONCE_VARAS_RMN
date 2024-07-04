package com.cibertec.entidad.services;

import java.util.List;

import com.cibertec.entidad.Producto;

public interface ProductoService {
	
	public abstract Producto registrarProducto(Producto obj);
	
	public abstract Producto actualizarProducto(Producto upda);
	
	public abstract List<Producto> listarProducto();
	
	public abstract Producto buscarPorId(int id);
	
	public abstract void eliminarProducto(int id);
}
