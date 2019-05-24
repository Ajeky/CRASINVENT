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

import com.salesianostriana.damcrasinvent.formbeans.SearchBean;
import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class InventController {

	private InventServicio inventservicio;
	private UsuarioServicio usuarioservicio;
	private ConceptosServicio concepservi;
	private CamposServicio campservi;
	private ValoresCamposServicio valorservi;

	/**
	 * @param inventservicio
	 */
	public InventController(InventServicio servicio, UsuarioServicio usuarioservicio, ConceptosServicio concepservi, CamposServicio campservi, ValoresCamposServicio valorservi) {
		this.inventservicio = servicio;
		this.usuarioservicio = usuarioservicio;
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.valorservi = valorservi;
	}

	@GetMapping({ "/inventList" })
	public String listarTodo(Model model) {
		model.addAttribute("lista", inventservicio.findAll());
		return "listas/listaInvent";
	}

	@GetMapping("/newInvent")
	public String mostrarFormulario(Model model) {
		model.addAttribute("invent", new Invent());
		return "forms/crearInvent";
	}

	@PostMapping("/newInvent/submit")
	public String procesarFormulario(@ModelAttribute("invent") Invent i, Principal principal) {
		i.setUsuario(usuarioservicio.buscarPorEmail(principal.getName()));
		inventservicio.add(i);
		return "redirect:/user/inventList";
	}

	@GetMapping("/editInvent/{id}")
	public String editarPorID(@PathVariable("id") long id, Model model) {

		Invent invEdit = inventservicio.findById(id);

		if (invEdit != null) {
			model.addAttribute("invent", invEdit);
			return "forms/crearInvent";
		} else {
			return "redirect:/user/inventList";
		}
	}
	
	@GetMapping("/detalleInvent/{id}")
	public String detalleInventario(@PathVariable("id") long id, Model model) {
		Invent i = inventservicio.findById(id);
		List<Conceptos> c = i.getConceptos();
		
		if (i != null) {
			model.addAttribute("invent", i);
			model.addAttribute("conceptos", c);
			return "listas/inventarioDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}

	@PostMapping("/editInvent/submit")
	public String procesarEdicion(@ModelAttribute("invent") Invent i, Principal principal) {
		i.setUsuario(usuarioservicio.buscarPorEmail(principal.getName()));
		inventservicio.edit(i);
		return "redirect:/user/inventList";
	}

	@GetMapping("/deleteInvent/{id}")
	public String borrar(@PathVariable("id") long id) {
		Invent invent = inventservicio.findById(id);
		
		for (Conceptos concepto : invent.getConceptos()) {
			for (Campos campo : concepto.getCampos()) {
				for (ValoresCampos valor : campo.getValoresCampos()) {
					valorservi.delete(valor);
				}
				campservi.delete(campo);
			}
			concepservi.delete(concepto);
		}
		
		inventservicio.delete(invent);
		
		return "redirect:/user/inventList";
	}

	@GetMapping("/user/inventList")
	public String mostrarInventsUsuario(Model model, Principal principal) {
		model.addAttribute("searchForm", new SearchBean());
		
		List<Invent> inventList = inventservicio
				.encontrarInventariosDeUnUsuario(usuarioservicio.buscarPorEmail(principal.getName()).getId());
		
		model.addAttribute("lista", inventList);
		return "/listas/listaInvent";
	}
	
	@PostMapping("/search")
	  public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean,
			 Model model){
		
		model.addAttribute("invent", new Invent());
	  	model.addAttribute("lista", inventservicio.findByNombre(searchBean.getSearch()));
	  
	  return "/listas/listaInvent";
	  }

}
