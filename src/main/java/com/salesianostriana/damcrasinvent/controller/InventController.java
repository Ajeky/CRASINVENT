/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping({"/", "/inventList"})
	public String listarTodo(Model model) {
		model.addAttribute("lista",inventservicio.findAll());
		return "listas/listaInvent";
	}
	
	@GetMapping("/newInvent")
	public String mostrarFormulario(Model model) {
		model.addAttribute("invent", new Invent());
		return "forms/crearInvent";
	}
	
	@PostMapping("/newInvent/submit")
	public String procesarFormulario(@ModelAttribute("invent") Invent i) {
		inventservicio.add(i);
		return"redirect:/";
	}
	
	@GetMapping("/editInvent/{id}")
	public String editarPorID(@PathVariable("id") long id, Model model) {
		
		Invent invEdit = inventservicio.findById(id);
		
		if(invEdit != null) {
			model.addAttribute("invent", invEdit);
			return "forms/crearInvent";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/editInvent/submit")
	public String procesarEdicion(@ModelAttribute("invent") Invent i) {
		inventservicio.edit(i);
		return "redirect:/";
	}
	
	@GetMapping("/deleteInvent/{id}")
	public String borrar(@PathVariable("id") long id) {
		inventservicio.deleteById(id);
		return "redirect:/";
	}

}
