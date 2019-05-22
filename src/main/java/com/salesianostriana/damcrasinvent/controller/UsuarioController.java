/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class UsuarioController {
	
	UsuarioServicio usuarioServicio;
	
	public UsuarioController(UsuarioServicio servicio) {
		this.usuarioServicio = servicio;
	}
	
	@GetMapping("/newUser")
	public String registroUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "forms/registro";
	}
	
	@PostMapping("/newUser/submit")
	public String procesarRegistro(@ModelAttribute("usuario") Usuario u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		usuarioServicio.add(u);
		return "redirect:/";
	}
	

}
