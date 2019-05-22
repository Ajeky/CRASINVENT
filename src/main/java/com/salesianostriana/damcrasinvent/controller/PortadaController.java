/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
public class PortadaController {

	UsuarioServicio usuarioServicio;

	public PortadaController(UsuarioServicio servicio) {
		this.usuarioServicio = servicio;
	}

	@GetMapping({ "/" })
	public String mostrarPortada(Principal principal) {
		if (principal != null) {
			return "/usuario/portada";
		} else {
			return "portada";
		}
	}

	@GetMapping("/portadaUser")
	public String mostrarPortadaUsuario() {
		return "/usuario/portada";
	}
}
