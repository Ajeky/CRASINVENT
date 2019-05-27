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
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioEmpresaServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioServicio userServi;
	private UsuarioEmpresaServicio empServi;
	private InventServicio invServi;
	private ConceptosServicio concepservi;
	private CamposServicio campservi;
	private ValoresCamposServicio valorservi;

	public AdminController(UsuarioServicio userServi, UsuarioEmpresaServicio empServi, InventServicio invServi,
			ConceptosServicio concepservi, CamposServicio campservi, ValoresCamposServicio valorservi) {
		this.userServi = userServi;
		this.empServi = empServi;
		this.invServi = invServi;
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.valorservi = valorservi;
	}

	@GetMapping("/")
	public String portada(Model model) {
		model.addAttribute("usuarios", userServi.findAll());
		model.addAttribute("empresas", empServi.findAll());
		return "admin/portada";
	}

	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(Model model, @PathVariable("id") long id) {
		Usuario aEditar = userServi.findById(id);

		if (aEditar != null) {
			model.addAttribute("usuario", aEditar);
			return "admin/editarUsuario";
		} else {
			return "redirect:/admin/";
		}
	}

	@PostMapping("/editarUsuario/submit")
	public String procesarEdicion(@ModelAttribute("usuario") Usuario u) {
		userServi.edit(u);
		return "redirect:/admin/";
	}

	@GetMapping("/detalleUsuario/{id}")
	public String detalleUsuario(@PathVariable("id") long id, Model model) {
		Usuario u = userServi.findById(id);
		List<Invent> i = u.getInvents();
		
		if (u != null) {
			model.addAttribute("usuario", u);
			model.addAttribute("invents", i);
			return "admin/detalleUsuario";
		} else {
			return "redirect:/admin/";
		}
	}

}
