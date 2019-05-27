
/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.formbeans.SearchBean;
import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.Usuario;
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
	public InventController(InventServicio servicio, UsuarioServicio usuarioservicio, ConceptosServicio concepservi,
			CamposServicio campservi, ValoresCamposServicio valorservi) {
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

	@GetMapping("/newInvent/{id}")
	public String mostrarFormulario(Model model, @PathVariable("id") long id) {
		Usuario u = usuarioservicio.findById(id);
		model.addAttribute("usuario", u);
		model.addAttribute("invent", new Invent());
		return "forms/crearInvent";
	}

	@PostMapping("/newInvent/submit/{id}")
	public String procesarFormulario(@ModelAttribute("invent") Invent i, HttpSession session,
			HttpServletRequest request, ModelMap modelMap, @PathVariable("id") long id) {

		i.setUsuario(usuarioservicio.findById(id));
		inventservicio.add(i);
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/detalleUsuario/" + id;
		} else if (request.isUserInRole("ROLE_USER")) {
			return "redirect:/user/inventList";
		} else {
			return "redirect:/acceso-denegado";
		}
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

	@PostMapping("/editInvent/submit")
	public String procesarEdicion(@ModelAttribute("invent") Invent i, Principal principal) {
		i.setUsuario(usuarioservicio.buscarPorEmail(principal.getName()));
		inventservicio.edit(i);
		return "redirect:/user/inventList";
	}

	@GetMapping("/detalleInvent/{id}")
	public String detalleInventario(@PathVariable("id") long id, Model model) {
		Invent i = inventservicio.findById(id);
		List<Conceptos> c = i.getConceptos();
		Usuario u = i.getUsuario();

		if (i != null) {
			model.addAttribute("invent", i);
			model.addAttribute("conceptos", c);
			model.addAttribute("usuario", u);
			return "listas/inventarioDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}

	@GetMapping("/deleteInvent/{id}")
	public String borrar(@PathVariable("id") long id, HttpSession session, HttpServletRequest request,
			ModelMap modelMap) {
		Invent invent = inventservicio.findById(id);
		long idUsuario = invent.getUsuario().getId();

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

		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/detalleUsuario/" + idUsuario;
		} else {
			return "redirect:/user/inventList";
		}
	}

	@GetMapping("/user/inventList")
	public String mostrarInventsUsuario(Model model, Principal principal) {
		model.addAttribute("searchForm", new SearchBean());

		Usuario u = usuarioservicio.buscarPorEmail(principal.getName());

		List<Invent> inventList = inventservicio.encontrarInventariosDeUnUsuario(u.getId());

		model.addAttribute("usuario", u);
		model.addAttribute("lista", inventList);
		return "/listas/listaInvent";
	}

	@PostMapping("/search")
	public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {

		model.addAttribute("invent", new Invent());
		model.addAttribute("lista", inventservicio.findByNombre(searchBean.getSearch()));

		return "/listas/listaInvent";
	}

}
