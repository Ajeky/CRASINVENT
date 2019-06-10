/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.HistoricoUsuarios;
import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.HistoricoUsuariosServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioEmpresaServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

/**
 * @author amarquez
 *
 */

@Controller
public class UsuarioController {

	UsuarioServicio usuarioServicio;
	HistoricoUsuariosServicio historicoServicio;
	UsuarioEmpresaServicio empresaServicio;

	public UsuarioController(UsuarioServicio servicio, HistoricoUsuariosServicio historicoServicio, UsuarioEmpresaServicio empresaServicio) {
		this.usuarioServicio = servicio;
		this.historicoServicio = historicoServicio;
		this.empresaServicio = empresaServicio;
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

	@GetMapping("/borrarCuenta")
	public String borrarCuenta(Principal principal) {
		Usuario aBorrar = usuarioServicio.buscarPorEmail(principal.getName());
		HistoricoUsuarios anadir = new HistoricoUsuarios();
		anadir.setId(aBorrar.getId());
		anadir.setNombre(aBorrar.getNombre());
		anadir.setApellidos(aBorrar.getApellidos());
		anadir.setEmail(aBorrar.getEmail());
		anadir.setNickname(aBorrar.getNickname());
		anadir.setPassword(aBorrar.getPassword());
		anadir.setTelefono(aBorrar.getTelefono());
		anadir.setAdmin(aBorrar.isAdmin());
		anadir.setCuentaCaducada(aBorrar.isCuentaCaducada());
		anadir.setCuentaBloqueada(aBorrar.isCuentaBloqueada());
		anadir.setCredencialesCaducadas(aBorrar.isCredencialesCaducadas());
		historicoServicio.add(anadir);
		usuarioServicio.delete(aBorrar);
		return "redirect:/logout";
	}

	@GetMapping("/user/configuracion")
	public String configuracion(Model model, Principal principal) {
		Usuario aConfigurar = usuarioServicio.buscarPorEmail(principal.getName());
		model.addAttribute("usuario", aConfigurar);
		return "/forms/configurarCuenta";
	}

	@GetMapping("/modificarDatos")
	public String modificarDatos(Model model, HttpSession session, HttpServletRequest request, ModelMap modelMap) {
		Usuario aModificar = usuarioServicio.buscarPorEmail(request.getUserPrincipal().getName());
		model.addAttribute("usuario", aModificar);

		if (request.isUserInRole("ROLE_PREMIUMUSER")) {
			return "/forms/modificarDatosPremium";
		} else if (request.isUserInRole("ROLE_USER")) {
			return "/forms/modificarDatos";
		} else {
			return "redirect:/acceso-denegado";
		}

	}

	@PostMapping("modificarDatos/submit")
	public String submitModificar(@ModelAttribute("usuario") Usuario u, HttpSession session, HttpServletRequest request,
			ModelMap modelMap, Model model) {
		usuarioServicio.edit(u);
		model.addAttribute("usuario", u);
		return "redirect:/user/configuracion";
	}
	
	

}
