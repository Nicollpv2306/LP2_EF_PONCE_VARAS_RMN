package com.cibertec.entidad.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;


import com.cibertec.entidad.Producto;
import com.cibertec.entidad.services.PDFService;
import com.cibertec.entidad.services.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@Autowired
	private PDFService servicePDF;
	
	@GetMapping("/listado")
	public String listarProductos(Model model) {
		List<Producto> producto = service.listarProducto();
		model.addAttribute("mensaje", producto);
		
		return "listado";
	}
	
	/*@GetMapping("/listareporte")
	public String listarProducto(Model model) {
		List<Producto> producto = service.listarProducto();
		model.addAttribute("producto", producto);
		
		return "reporteProductos";
	}  */
	
	// mostrar el formulario
	@GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
	    model.addAttribute("producto", new Producto());
	    return "registrarProducto";
	}
	
	@PostMapping("/registrar")
	//@ResponseBody
	public String registrarProducto(@ModelAttribute Producto obj, HttpSession session) {
		Producto producto = service.registrarProducto(obj);
		
		try {
			if (producto != null) {
				session.setAttribute("mensaje", "Registro Exitoso");
			}else {
				session.setAttribute("mensaje", "Registro Fallido");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/producto/listado";  // se regresa al end point que maneja 
	}
	
	// mostrar formulario de actualizar, mandar los datos segun ID (en este caso la fila seleccionada)
	 @GetMapping("/actualizar/{id}")
	 public String mostrarFormularioActualizar(@PathVariable("id") int id, Model model) {
	     Producto producto = service.buscarPorId(id);
	     model.addAttribute("producto", producto);
	     return "actualizarProducto"; 
	  }
	 
	 // procedemos a mandar los cambios para actualizar correctamente
	@PostMapping("/actualizar/{id}")
	//@ResponseBody
	public String actualizarProducto(@PathVariable("id") int id, @ModelAttribute Producto upda, Model model, HttpSession session) {
		upda.setId(id);
		
		Producto update = service.actualizarProducto(upda);
		if (update != null) {
			session.setAttribute("mensaje", "Actualizacion Exitosa");
		}else {
			session.setAttribute("mensaje", "Actualizacion Fallida");
		}
		return "redirect:/producto/listado";
	}
	
	// mostrar nterfaz de detalle
	 @GetMapping("/eliminar/{id}")
	 public String mostrarDetalleEliminar(@PathVariable("id") int id, Model model) {
	     Producto producto = service.buscarPorId(id);
	     model.addAttribute("producto", producto);
	     return "detalleProducto"; 
	  }
	
	@PostMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") int id ) {
		service.eliminarProducto(id);
		return "redirect:/producto/listado";
	}
	
	
	// reportar productos en PDF
	@GetMapping("/generar_reporte_enPDF")
	public ResponseEntity<InputStreamResource> generarReporteProducto(HttpSession session) throws IOException {
	    
	    List<Producto> productos = service.listarProducto();
	    
	    Map<String, Object> datosSacados = new HashMap<String, Object>();
	    datosSacados.put("producto", productos);

	
	    ByteArrayInputStream pdfBytes = servicePDF.generarPdfDeHtml("reporteProductos", datosSacados);

	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.add("Content-Disposition", "inline; filename=productos.pdf");
	    // Retornar la respuesta con el PDF generado
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(new InputStreamResource(pdfBytes));
	}
	
}
