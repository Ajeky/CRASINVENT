/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.Invent;
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
	
	@GetMapping("/newInvent")
	public String mostrarFormulario(Model model) {
		model.addAttribute("alumno", new Invent());
		return "crearInvent";
	}
	
	@PostMapping("/newInvent/submit")
	public String procesarFormulario(@ModelAttribute("invent") Invent i) {
		inventservicio.add(i);
		return"redirect:/";
	}
	

}
