/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.salesianostriana.damcrasinvent.excepciones.ExcepcionCaducidad;

/**
 * @author pedom
 *
 */

@ControllerAdvice
public class ExcepcionCaducidadController {
	
	@ExceptionHandler (ExcepcionCaducidad.class)
	public String excepcioncaducidad (Model model, ExcepcionCaducidad ec) {
		model.addAttribute("excepcion", ec);
		return "excepcionCaducidad";
	}

}
