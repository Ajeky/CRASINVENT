/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
public class PortadaController {

	@GetMapping({"/"})
	public String mostrarPortada() {
		return "portada";
	}
	
	@GetMapping("/portadaUser")
	public String mostrarPortadaUsuario() {
		return "/usuario/portada";
	}
}
