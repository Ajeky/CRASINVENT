/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

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
import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * Clase que controla las peticiones GET y POST que tienen que ver con objetos
 * Campos {@link com.salesianostriana.damcrasinvent.model.Campos}. Los atributos
 * de la clase son los servicios necesarios para gestionar las peticiones que se
 * realizan.
 * 
 * @author Álvaro Márquez
 *
 */

@Controller
public class CamposController {

	private CamposServicio camposservicio;
	private ValoresCamposServicio valorservicio;
	private ConceptosServicio concepservi;

	public CamposController(CamposServicio camposservicio, ValoresCamposServicio valorservicio,
			ConceptosServicio concepservi) {
		this.camposservicio = camposservicio;
		this.valorservicio = valorservicio;
		this.concepservi = concepservi;
	}

	/**
	 * Método que maneja la plantilla de los detalles de un campo. En ella se ve el
	 * nombre del campo y los valores que contiene. Se pueden añadir valores nuevos.
	 * 
	 * @param id    ID del campo a mostrar.
	 * @param model Como modelo se pasan el campo a mostrar y una lista con los
	 *              valores que contiene
	 * @return Redirige al usuario a la página de detalles del campo, o, en caso de
	 *         que se pase un campo nulo, a la lista de inventarios.
	 */
	@GetMapping("/detalleCampo/{id}")
	public String detalleConcepto(@PathVariable("id") long id, Model model) {
		Campos c = camposservicio.findById(id);
		List<ValoresCampos> ca = c.getValoresCampos();

		if (c != null) {
			model.addAttribute("campo", c);
			model.addAttribute("campos", ca);
			return "listas/campoDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}

	/**
	 * Método que controla la petición GET del formulario de creación de campos.
	 * 
	 * @param model Se le pasan como modelos un campo vacío al que se le
	 *              introducirán los datos en el formulario y y el Concepto al que
	 *              pertenece dicho campo
	 * @param id    ID del concepto al que pertenece el campo
	 * @return La plantilla de creación de Campo
	 */
	@GetMapping("/newCampo/{id}")
	public String nuevoCampo(Model model, @PathVariable("id") long id) {
		model.addAttribute("campo", new Campos());
		model.addAttribute("concepto", concepservi.findById(id));
		List<String> datos = new ArrayList<String>();
		datos.add("Integer");
		datos.add("String");
		datos.add("Date");
		model.addAttribute("datos", datos);	
		return "forms/crearCampo";

	}

	/**
	 * Método que controla la petición POST de creación de un campo
	 * 
	 * @param c  Campo a editar
	 * @param id ID del concepto al que pertenece el campo
	 * @return La plantilla de detalles del concepto
	 */

	@PostMapping("/newCampo/submit/{id}")
	public String procesarNuevoCampo(@ModelAttribute("campo") Campos c, @PathVariable("id") long id) {
		c.setConcepto(concepservi.findById(id));
		camposservicio.add(c);
		return "redirect:/detalleConcepto/" + id;
	}

	/**
	 * Método que gestiona la petición GET del formulario de edición del campo.
	 * 
	 * @param id    ID del campo a editar
	 * @param model Como modelo se pasan el campo a editar y el concepto al que
	 *              pertenece
	 * @return Si el campo es nulo devuelve al usuario a la portada, si no, devuelve
	 *         la plantilla de creación del campo
	 */
	@GetMapping("/editCampo/{id}")
	public String editarCampo(@PathVariable("id") long id, Model model) {
		Campos c = camposservicio.findById(id);

		if (c != null) {
			model.addAttribute("campo", c);
			model.addAttribute("concepto", c.getConcepto());
			return "forms/crearCampo";
		} else {
			return "redirect:/";
		}
	}

	/**
	 * Método que gestiona la petición POST de la edición de campos.
	 * 
	 * @param c  campo a editar
	 * @param id ID del concepto al que pertenece el campo
	 * @return La plantilla de detalles del concepto
	 */
	@PostMapping("/editCampo/submit/{id}")
	public String procesarEdicionCampo(@ModelAttribute("campo") Campos c, @PathVariable("id") long id) {
		c.setConcepto(concepservi.findById(id));
		camposservicio.edit(c);
		return "redirect:/detalleConcepto/" + id;
	}

	/**
	 * Método que gestiona la petición de borrar un campo. Borra todos los valores
	 * que estuvieran relacionados con el campo en cuestión
	 * 
	 * @param id ID del campo a borrar
	 * @return La plantilla de detalles del concepto al que pertenecía el campo
	 */
	@GetMapping("/deleteCampo/{id}")
	public String borrarCampo(@PathVariable("id") long id) {
		Campos campo = camposservicio.findById(id);
		long concepID = campo.getConcepto().getId();

		for (ValoresCampos valor : campo.getValoresCampos()) {
			valorservicio.delete(valor);
		}

		camposservicio.delete(campo);

		return "redirect:/detalleConcepto/" + concepID;
	}
}
