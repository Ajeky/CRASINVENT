/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

/**
 * Página que maneja las URLs que devuelven a la portada ya sea de usuario, de
 * persona sin logear o de admin.
 * 
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
	public String mostrarPortada(HttpSession session, HttpServletRequest request, ModelMap modelMap, Model model) {

		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/";
		} else if (request.isUserInRole("ROLE_USER")) {
			model.addAttribute("usuario", usuarioServicio.buscarPorEmail(request.getUserPrincipal().getName()));
			return "/usuario/portada";
		} else if (request.isUserInRole("ROLE_PREMIUMUSER")) {
			model.addAttribute("usuario", usuarioServicio.buscarPorEmail(request.getUserPrincipal().getName()));
			return "/usuario/portada";

		} else {
			return "portada";
		}
	}

	@GetMapping("/portadaUser")
	public String mostrarPortadaUsuario() {
		return "/usuario/portada";
	}
	
	@GetMapping("/atras")
	public String volverAtras(HttpServletRequest request) {
		return request.getHeader("referer");
	}
}
