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
import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class ConceptosController {
	
	ConceptosServicio concepservi;
	CamposServicio campservi;
	InventServicio inventservi;
	ValoresCamposServicio valorservi;
	
	public ConceptosController(ConceptosServicio concepservi, CamposServicio campservi, InventServicio inventservi, ValoresCamposServicio valorservi) {
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.inventservi = inventservi;
		this.valorservi = valorservi;
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
	
	@PostMapping("/newConcepto/submit/{id}")
	public String procesarFormulario(@ModelAttribute("concepto") Conceptos c, @PathVariable("id") long id) {
		c.setInvent(inventservi.findById(id));
		concepservi.add(c);
		return "redirect:/detalleInvent/" + id;
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
	
	@PostMapping("/editConcepto/submit/{id}")
	public String editarConcepto(@ModelAttribute("concepto") Conceptos c, @PathVariable("id") long id) {
		c.setInvent(inventservi.findById(id));
		concepservi.edit(c);
		return "redirect:/detalleInvent/" + id;
	}
	
	@GetMapping("/deleteConcepto/{id}")
	public String borrarConcepto(@PathVariable("id") long id) {
		Conceptos concepto = concepservi.findById(id);
		long inventID = concepto.getInvent().getId();

		for (Campos campo : concepto.getCampos()) {
			for (ValoresCampos valor : campo.getValoresCampos()) {
				valorservi.delete(valor);
			}
			campservi.delete(campo);
			
		}
		concepservi.delete(concepto);
		
		
		return "redirect:/detalleInvent/" + inventID;
	}

}
