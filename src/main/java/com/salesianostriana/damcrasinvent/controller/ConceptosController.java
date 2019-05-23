/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class ConceptosController {
	
	ConceptosServicio concepservi;
	CamposServicio campservi;
	
	public ConceptosController(ConceptosServicio concepservi, CamposServicio campservi) {
		this.concepservi = concepservi;
		this.campservi = campservi;
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

}
