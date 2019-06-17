/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;
import java.util.ArrayList;
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
 * Clase que controla las peticiones GET y POST que tienen que ver con objetos
 * Conceptos {@link com.salesianostriana.damcrasinvent.model.Conceptos}. Los
 * atributos de la clase son los servicios necesarios para gestionar las
 * peticiones que se realizan.
 * 
 * @author Álvaro Márquez
 *
 */

@Controller
public class ConceptosController {

	ConceptosServicio concepservi;
	CamposServicio campservi;
	InventServicio inventservi;
	ValoresCamposServicio valorservi;

	public ConceptosController(ConceptosServicio concepservi, CamposServicio campservi, InventServicio inventservi,
			ValoresCamposServicio valorservi) {
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.inventservi = inventservi;
		this.valorservi = valorservi;
	}

	/**
	 * Método que maneja la plantilla de los detalles de un campo. En ella se ve el
	 * nombre del Concepto y los Campos que contiene. Se pueden añadir Campos
	 * nuevos.
	 * 
	 * @param ID    id del Concepto a mostrar
	 * @param model Se pasan como modelo el concepto a mostrar y una lista con los
	 *              campos que contiene
	 * @return La plantilla de detalles del concepto. Si el concepto es nulo,
	 *         devuelve al usuario a la lista de inventarios
	 */
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

	/**
	 * Método que controla la petición GET del formulario de creación de Conceptos.
	 * 
	 * @param model Se le pasan como modelos un Concepto vacío al que se le
	 *              introducirán los datos en el formulario y y el Invent al que
	 *              pertenece dicho Concepto
	 * @param id    ID del Invent al que pertenece el Concepto
	 * @return La plantilla de creación de Conceptos
	 */
	@GetMapping("/newConcepto/{id}")
	public String nuevoConcepto(Model model, @PathVariable("id") long id) {
		model.addAttribute("concepto", new Conceptos());
		model.addAttribute("invent", inventservi.findById(id));
		return "forms/crearConcepto";
	}

	/**
	 * Método que controla la petición POST de creación de un Concepto
	 * 
	 * @param c  Concepto a crear
	 * @param id ID del Inventario al que pertenece el Concepto
	 * @return La plantilla de detalles del Inventario
	 */
	@PostMapping("/newConcepto/submit/{id}")
	public String procesarFormulario(@ModelAttribute("concepto") Conceptos c, @PathVariable("id") long id) {
		c.setInvent(inventservi.findById(id));
		concepservi.add(c);
		return "redirect:/detalleInvent/" + id;
	}

	/**
	 * Método que gestiona la petición GET del formulario de edición del Concepto.
	 * 
	 * @param id    ID del Concepto a editar
	 * @param model Como modelo se pasan el Concepto a editar y el Inventario al que
	 *              pertenece
	 * @return Si el Concepto es nulo devuelve al usuario a la portada, si no,
	 *         devuelve la plantilla de creación del Concepto
	 */
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

	/**
	 * Método que gestiona la petición POST de la edición de Conceptos.
	 * 
	 * @param c  Concepto a editar
	 * @param id ID del Inventario al que pertenece el Concepto
	 * @return La plantilla de detalles del Inventario
	 */
	@PostMapping("/editConcepto/submit/{id}")
	public String editarConcepto(@ModelAttribute("concepto") Conceptos c, @PathVariable("id") long id) {
		c.setInvent(inventservi.findById(id));
		concepservi.edit(c);
		return "redirect:/detalleInvent/" + id;
	}

	/**
	 * Método que gestiona la petición de borrar un Concepto. Borra todos los campos
	 * y valores que estuvieran relacionados con el Concepto en cuestión
	 * 
	 * @param id ID del Concepto a borrar
	 * @return La plantilla de detalles del Inventario al que pertenecía el Concepto
	 */

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
	
	@GetMapping("/imprimirConcepto/{id}")
	public String imprimirConcepto(@PathVariable("id") long id, Model model) {
		Conceptos concepto = concepservi.findById(id);
		
		List<Campos> campos = concepto.getCampos();
		List<List<ValoresCampos>> valores = new ArrayList<List<ValoresCampos>>();
		for (Campos c : campos) {
			valores.add(c.getValoresCampos());
		}
		
		model.addAttribute("concepto", concepto);
		model.addAttribute("campos", campos);
		model.addAttribute("valores", valores);
		
		return "/listas/imprimirConcepto";
		
	}

}
