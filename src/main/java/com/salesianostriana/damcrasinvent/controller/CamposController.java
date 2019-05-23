/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class CamposController {
	
	private CamposServicio camposservicio;
	private ValoresCamposServicio valorservicio;
	private ConceptosServicio concepservi;

	/**
	 * @param camposservicio
	 */
	public CamposController(CamposServicio camposservicio, ValoresCamposServicio valorservicio, ConceptosServicio concepservi) {
		this.camposservicio = camposservicio;
		this.valorservicio = valorservicio;
		this.concepservi = concepservi;
	}
	
	@GetMapping("/detalleCampo/{id}")
	public String detalleConcepto(@PathVariable("id") long id, Model model) {
		Campos c = camposservicio.findById(id);
		List<ValoresCampos> ca = c.getValoresCampos();
				
		if (c != null) {
			model.addAttribute("campo", c);
			model.addAttribute("campos", ca);
			return "listas/camposDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}
	
	@GetMapping("/newCampo/{id}")
	
	
}
