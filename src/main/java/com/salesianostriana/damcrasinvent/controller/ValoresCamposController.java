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

import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
public class ValoresCamposController {
	
	private ValoresCamposServicio valorservi;
	private CamposServicio campservi;
	
	/**
	 * @param valorservi
	 * @param campservi
	 */
	public ValoresCamposController(ValoresCamposServicio valorservi, CamposServicio campservi) {
		this.valorservi = valorservi;
		this.campservi = campservi;
	}
	
	@GetMapping("/newValor/{id}")
	public String nuevoValor (Model model, @PathVariable("id") long id) {
		model.addAttribute("valorCampo", new ValoresCampos());
		model.addAttribute("campo", campservi.findById(id));
		return "forms/crearValor";
	}
	
	@PostMapping("/newValor/submit/{id}")
	public String procesarNuevoValor(@ModelAttribute("valorCampo") ValoresCampos v, @PathVariable("id") long id) {
		v.setCampo(campservi.findById(id));
		v.setConcepto(v.getCampo().getConcepto());
		valorservi.add(v);
		return "redirect:/detalleCampo/" + id;
	}
	
	@GetMapping("/editValor/{id}")
	public String editarValor(@PathVariable("id") long id, Model model) {
		ValoresCampos v = valorservi.findById(id);
		
		if (v != null) {
			model.addAttribute("valorCampo", v);
			model.addAttribute("campo", v.getCampo());
			return "forms/crearValor";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/editValor/submit/{id}")
	public String procesarEdicionCampo(@ModelAttribute("valorCampo") ValoresCampos v, @PathVariable("id") long id) {
		v.setCampo(campservi.findById(id));
		v.setConcepto(v.getCampo().getConcepto());
		valorservi.edit(v);
		return "redirect:/detalleCampo/" + id;
	}
	
	@GetMapping("/deleteValor/{id}")
	public String borrarValor(@PathVariable("id") long id) {
		long campoID = valorservi.findById(id).getCampo().getId();
		
		valorservi.deleteById(id);
		
		return "redirect:/detalleCampo/" + campoID;
	}

}
