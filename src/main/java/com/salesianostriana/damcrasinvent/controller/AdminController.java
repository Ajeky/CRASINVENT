/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String portada() {
		return "admin/portada";
	}

}
