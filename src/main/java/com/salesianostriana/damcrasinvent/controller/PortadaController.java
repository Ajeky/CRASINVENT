/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public String mostrarPortada(HttpSession session, HttpServletRequest request,  ModelMap modelMap) {
		
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/";
		}
		else if (request.isUserInRole("ROLE_USER")) {
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
