/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class ConceptosController {
	
	ConceptosServicio concepservi;
	CamposServicio campservi;
	InventServicio inventservi;
	
	public ConceptosController(ConceptosServicio concepservi, CamposServicio campservi, InventServicio inventservi) {
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.inventservi = inventservi;
	}
	
	@GetMapping("/detalleConcepto/{id}")
	public String detalleConcepto(@PathVariable("id") long id, Model model) {
		Conceptos c = concepservi.findById(id);
		List<Campos> ca = c.getCampos();
		
		if (c != null) {
			model.addAttribute("concepto", c);
			model.addAttribute("campos", ca);
			return "listas/conceptoDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}
	
	@GetMapping("/newConcepto/{id}")
	public String nuevoConcepto(Model model, @PathVariable("id") long id) {
		model.addAttribute("concepto", new Conceptos());
		model.addAttribute("invent", inventservi.findById(id));
		return "forms/crearConcepto";
	}
	
	@PostMapping("/newConcepto/submit")
	public String procesarFormulario(@ModelAttribute("concepto") Conceptos c) {
		concepservi.add(c);
		long inventID = c.getInvent().getId();
		return "redirect:/detalleInvent/" + inventID;
	}
	
	@GetMapping("/editConcepto/{id}")
	public String editarConcepto(@PathVariable("id") long id, Model model) {
		Conceptos c = concepservi.findById(id);
		
		if (c != null) {
			model.addAttribute("concepto", c);
			model.addAttribute("invent", c.getInvent());
			return "forms/crearConcepto";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/editConcepto/submit")
	public String editarConcepto(@ModelAttribute("concepto") Conceptos c) {
		concepservi.edit(c);
		return "redirect:/detalleInvent/" + c.getInvent().getId();
	}
	
	@GetMapping("/deleteConcepto/{id}")
	public String borrarConcepto(@PathVariable("id") long id) {
		long inventID = concepservi.findById(id).getInvent().getId();
		concepservi.deleteById(id);
		return "redirect:/detalleInvent/" + inventID;
	}

}
