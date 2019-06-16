/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.damcrasinvent.model.PayPal;
import com.salesianostriana.damcrasinvent.model.Tarjeta;
import com.salesianostriana.damcrasinvent.model.Transferencia;
import com.salesianostriana.damcrasinvent.model.UsuarioEmpresa;
import com.salesianostriana.damcrasinvent.servicios.MetodosPagoServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioEmpresaServicio;

/**
 * @author Álvaro Márquez
 *
 */

@Controller
public class MetodosPagoController {

	private MetodosPagoServicio metodosservicio;
	private UsuarioEmpresaServicio empresaservi;

	public MetodosPagoController(MetodosPagoServicio metodosservicio, UsuarioEmpresaServicio empresaservi) {
		this.metodosservicio = metodosservicio;
		this.empresaservi = empresaservi;
	}

	@GetMapping("/tarjeta")
	public String nuevaTarjeta(Model model) {
		model.addAttribute("tarjeta", new Tarjeta());
		return "forms/crearTarjeta";
	}

	@PostMapping("/tarjeta/submit")
	public String submitTarjeta(@ModelAttribute("tarjeta") Tarjeta t, HttpSession session, HttpServletRequest request,
			ModelMap modelMap) {
		UsuarioEmpresa u = empresaservi.buscarPorEmail(request.getUserPrincipal().getName());

		t.getUsuarios().add(u);
		u.getMetodosPago().add(t);

		metodosservicio.add(t);

		if (request.isUserInRole("ROLE_PREMIUMUSER")) {
			return "redirect:/";
		} else {
			return "redirect:/logout";
		}
	}

	@GetMapping("/transferencia")
	public String nuevaTransferencia(Model model) {
		model.addAttribute("transferencia", new Transferencia());
		return "forms/crearTransferencia";
	}

	@PostMapping("/transferencia/submit")
	public String submitTransferencia(@ModelAttribute("transferencia") Transferencia t, HttpSession session,
			HttpServletRequest request, ModelMap modelMap) {
		UsuarioEmpresa u = empresaservi.buscarPorEmail(request.getUserPrincipal().getName());

		t.getUsuarios().add(u);
		u.getMetodosPago().add(t);

		metodosservicio.add(t);

		if (request.isUserInRole("ROLE_PREMIUMUSER")) {
			return "redirect:/";
		} else {
			return "redirect:/logout";
		}
	}

	@GetMapping("/paypal")
	public String nuevoPayPal(Model model) {
		model.addAttribute("paypal", new PayPal());

		return "forms/crearPayPal";
	}

	@PostMapping("/paypal/submit")
	public String submitPayPal(@ModelAttribute("paypal") PayPal p, HttpSession session, HttpServletRequest request,
			ModelMap modelMap) {

		UsuarioEmpresa u = empresaservi.buscarPorEmail(request.getUserPrincipal().getName());

		p.getUsuarios().add(u);
		u.getMetodosPago().add(p);

		metodosservicio.add(p);

		if (request.isUserInRole("ROLE_PREMIUMUSER")) {
			return "redirect:/";
		} else {
			return "redirect:/logout";
		}
	}

}
