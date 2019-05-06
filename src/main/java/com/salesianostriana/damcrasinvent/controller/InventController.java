/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.damcrasinvent.servicios.InventServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class InventController {
	
	private InventServicio inventservicio;

	/**
	 * @param inventservicio
	 */
	public InventController(InventServicio servicio) {
		this.inventservicio = servicio;
	}
	
	@GetMapping({"/inventList"})
	public String listarTodo(Model model) {
		model.addAttribute("lista",inventservicio.findAll());
		return "listaInvent";
	}
	

}
