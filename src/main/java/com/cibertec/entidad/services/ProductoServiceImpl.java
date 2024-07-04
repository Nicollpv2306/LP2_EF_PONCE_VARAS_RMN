package com.cibertec.entidad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.entidad.Producto;
import com.cibertec.repository.ProductoRepository;

@Service
public class ProductoServiceImpl  implements ProductoService{

	@Autowired
	private ProductoRepository repository;
	
	@Override
	public Producto registrarProducto(Producto obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public Producto actualizarProducto(Producto upda) {
		// TODO Auto-generated method stub
		return repository.save(upda);
	}

	@Override
	public List<Producto> listarProducto() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Producto buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void eliminarProducto(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
