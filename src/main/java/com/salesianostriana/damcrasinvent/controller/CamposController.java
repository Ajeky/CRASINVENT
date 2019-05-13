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

import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class CamposController {
	
	private CamposServicio camposservicio;

	/**
	 * @param camposservicio
	 */
	public CamposController(CamposServicio camposservicio) {
		this.camposservicio = camposservicio;
	}
	
	@GetMapping({"/camposList"})
	public String listarCampos(Model model) {
		model.addAttribute("lista",camposservicio.findAll());
		return "listaCampos";
	}
	
	@GetMapping({"/newCampo"})
	public String formNuevo(Model model) {
		model.addAttribute("campo", new Campos());
		return "nuevoCampo";
	}
	
	@PostMapping("/newCampo/submit")
	public String procesarFrom(@ModelAttribute("campo") Campos c) {
		camposservicio.add(c);
		return "redirect:/";
	}
	
	@GetMapping("/editCampo/{id}")
	public String editarPorID(@PathVariable("id") long id, Model model) {
		Campos camEdit = camposservicio.findById(id);
		
		if(camEdit != null) {
			model.addAttribute("campo", camEdit);
			return "nuevoCampo";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/editCampo/submit")
	public String procesarEdit(@ModelAttribute("campo") Campos c) {
		camposservicio.edit(c);
		return "redirect:/";
	}
	
	@GetMapping("/deleteCampo/{id}")
	public String borrar(@PathVariable("id") long id) {
		camposservicio.deleteById(id);
		return "redirect:/";
	}
	
	

}
