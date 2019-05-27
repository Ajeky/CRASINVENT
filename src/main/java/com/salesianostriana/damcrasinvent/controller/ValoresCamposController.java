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
 * Clase que controla las peticiones GET y POST que tienen que ver con objetos
 * ValoresCampos {@link com.salesianostriana.damcrasinvent.model.ValoresCampos}.
 * Los atributos de la clase son los servicios necesarios para gestionar las
 * peticiones que se realizan.
 * 
 * @author Álvaro Márquez
 *
 */

@Controller
public class ValoresCamposController {

	private ValoresCamposServicio valorservi;
	private CamposServicio campservi;

	public ValoresCamposController(ValoresCamposServicio valorservi, CamposServicio campservi) {
		this.valorservi = valorservi;
		this.campservi = campservi;
	}

	/**
	 * Método que controla la petición GET del formulario de creación de valores.
	 * 
	 * @param model Se le pasan como modelos un valor vacío al que se le
	 *              introducirán los datos en el formulario y y el campo al que
	 *              pertenece dicho valor
	 * @param id    ID del campo al que pertenece el valor
	 * @return La plantilla de creación de Valores
	 */
	@GetMapping("/newValor/{id}")
	public String nuevoValor(Model model, @PathVariable("id") long id) {
		model.addAttribute("valorCampo", new ValoresCampos());
		model.addAttribute("campo", campservi.findById(id));
		return "forms/crearValor";
	}

	/**
	 * Método que controla la petición POST de creación de un valor
	 * 
	 * @param v  Valor a editar
	 * @param id ID del campo al que pertenece el valor
	 * @return La plantilla de detalles del concepto
	 */
	@PostMapping("/newValor/submit/{id}")
	public String procesarNuevoValor(@ModelAttribute("valorCampo") ValoresCampos v, @PathVariable("id") long id) {
		v.setCampo(campservi.findById(id));
		v.setConcepto(v.getCampo().getConcepto());
		valorservi.add(v);
		return "redirect:/detalleCampo/" + id;
	}

	/**
	 * Método que gestiona la petición GET del formulario de edición del valor.
	 * 
	 * @param id    ID del valor a editar
	 * @param model Como modelo se pasan el valor a editar y el campo al que
	 *              pertenece
	 * @return Si el valor es nulo devuelve al usuario a la portada, si no, devuelve
	 *         la plantilla de creación del valor
	 */
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

	/**
	 * Método que gestiona la petición POST de la edición de valores.
	 * 
	 * @param v  valor a editar
	 * @param id ID del campo al que pertenece el campo
	 * @return La plantilla de detalles del Campo
	 */
	@PostMapping("/editValor/submit/{id}")
	public String procesarEdicionCampo(@ModelAttribute("valorCampo") ValoresCampos v, @PathVariable("id") long id) {
		v.setCampo(campservi.findById(id));
		v.setConcepto(v.getCampo().getConcepto());
		valorservi.edit(v);
		return "redirect:/detalleCampo/" + id;
	}

	/**
	 * Método que gestiona la petición de borrar un valor.
	 * 
	 * @param id ID del valor a borrar
	 * @return La plantilla de detalles del campo al que pertenecía el valor
	 */
	@GetMapping("/deleteValor/{id}")
	public String borrarValor(@PathVariable("id") long id) {
		long campoID = valorservi.findById(id).getCampo().getId();

		valorservi.deleteById(id);

		return "redirect:/detalleCampo/" + campoID;
	}

}
