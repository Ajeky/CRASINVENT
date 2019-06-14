/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.damcrasinvent.formbeans.SearchBean;
import com.salesianostriana.damcrasinvent.model.HistoricoUsuarios;
import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.Pager;
import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.HistoricoUsuariosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioEmpresaServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * Clase que controla las peticiones GET y POST que realizan los
 * administradores. Los atributos de la clase son todos los servicios necesarios
 * para gestionar las peticiones que se realizan.
 * 
 * @author Álvaro Márquez
 *
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UsuarioServicio userServi;
	private UsuarioEmpresaServicio empServi;
	private InventServicio invServi;
	private ConceptosServicio concepservi;
	private CamposServicio campservi;
	private ValoresCamposServicio valorservi;
	private HistoricoUsuariosServicio histoServi;
	
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	public AdminController(UsuarioServicio userServi, UsuarioEmpresaServicio empServi, InventServicio invServi,
			ConceptosServicio concepservi, CamposServicio campservi, ValoresCamposServicio valorservi,
			HistoricoUsuariosServicio histoServi) {
		this.userServi = userServi;
		this.empServi = empServi;
		this.invServi = invServi;
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.valorservi = valorservi;
		this.histoServi = histoServi;
	}

	/**
	 * Método que maneja la portada que verán los admins al logearse o pulsar la
	 * tecla inicio.
	 * 
	 * @param model Se le pasa como modelo una lista con todos los usuarios
	 *              existentes en la base de datos
	 * @return La plantilla de la portada para los admins
	 */
	@GetMapping({"/", "/buscarUser"})
	public String portada(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("email") Optional<String> email,
			Model model) {

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
				// el tamaño de página inicial.
				int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

				// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
				// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
				// del parámetro decrementado en 1.
				int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

				String evalNombre = email.orElse(null);
				
				Page<Usuario> usuarios = null;
				
				if (evalNombre == null) {
					usuarios = userServi.findAllPageable(PageRequest.of(evalPage, evalPageSize));
				} else {
					usuarios = userServi.findByEmailPageable(evalNombre, PageRequest.of(evalPage, evalPageSize));
				}
				
				Pager pager = new Pager(usuarios.getTotalPages(), usuarios.getNumber(), BUTTONS_TO_SHOW);
				
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("selectedPageSize", evalPageSize);
				model.addAttribute("pageSizes", PAGE_SIZES);
				model.addAttribute("pager", pager);
				
		return "admin/portada";
	}
	
	@PostMapping("")
	public String searchUsuario(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {
		model.addAttribute("usuarios", userServi.findByEmail(searchBean.getSearch()));
		
		return "admin/portada";
	}
	

	/**
	 * Método que maneja el formulario de edición de un usuario.
	 * 
	 * @param model Se le pasa como modelo el usuario a editar
	 * @param id    ID del usuario a editar
	 * @return En caso de que el usuario cuyo id se ha pasado sea nulo, devuelve al
	 *         admin a la portada. Si no, devuelve la plantilla del formulario de
	 *         edición.
	 */
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(Model model, @PathVariable("id") long id) {
		Usuario aEditar = userServi.findById(id);

		if (aEditar != null) {
			model.addAttribute("usuario", aEditar);
			return "admin/editarUsuario";
		} else {
			return "redirect:/admin/";
		}
	}

	/**
	 * Método que controla la petición POST de la edición de usuarios
	 * 
	 * @param u El usuario a editar
	 * @return Devuelve al admin a la portada
	 */
	@PostMapping("/editarUsuario/submit")
	public String procesarEdicion(@ModelAttribute("usuario") Usuario u) {
		userServi.edit(u);
		return "redirect:/admin/";
	}

	/**
	 * Método que controla la plantilla de los detalles de un usuario. En ella se
	 * ven los datos del usuario y una lista de sus inventarios. Se puede editar y
	 * borrar al usuario y crear nuevos inventarios pertenecientes al usuario.
	 * También se pueden borrar inventarios ya existentes.
	 * 
	 * @param id    ID del usuario a mostrar.
	 * @param model Se le pasan como parámetros el usuario a mostrar y una lista de
	 *              sus inventarios
	 * @return Si el usuario es nulo devuelve al admin a la portada, si no, devuelve
	 *         la plantilla de los detalles del usuario
	 */
	@GetMapping("/detalleUsuario/{id}")
	public String detalleUsuario(@PathVariable("id") long id, Model model) {
		Usuario u = userServi.findById(id);
		List<Invent> i = u.getInvents();

		if (u != null) {
			model.addAttribute("usuario", u);
			model.addAttribute("invents", i);
			return "admin/detalleUsuario";
		} else {
			return "redirect:/admin/";
		}
	}

	/**
	 * Método que controla la petición de borrar un usuario. Guarda todos los datos
	 * del usuario a borrar en un objeto de la tabla
	 * HistoricoUsuarios{@link com.salesianostriana.damcrasinvent.model.HistoricoUsuarios}
	 * 
	 * @param id ID del usuario a borrar
	 * @return Devuelve al admin a la portada
	 */
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable("id") long id) {
		Usuario aBorrar = userServi.findById(id);
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
		histoServi.add(anadir);
		userServi.delete(aBorrar);
		return "redirect:/admin/";
	}

	/**
	 * Método que maneja la petición get de editar un inventario de un usuario.
	 * 
	 * @param id        ID del inventario a editar
	 * @param idUsuario ID del usuario al que pertenece el inventario. Lo utiliza
	 *                  para redirigir al admin de vuelta a la página de detalles
	 *                  del usuario en caso de que se intente acceder a un invent
	 *                  nulo
	 * @param model     Se le pasan como parámetros el inventario a editar y el
	 *                  usuario al que pertenece
	 * @return Si el inventario es nulo devuelve al admin a la página de detalles
	 *         del usuario, si no, devuelve la plantilla de editar el inventario
	 */
	@GetMapping("/editInvent/{id}/{idUsuario}")
	public String editarInventario(@PathVariable("id") long id, @PathVariable("idUsuario") long idUsuario,
			Model model) {
		Invent aEditar = invServi.findById(id);
		Usuario pertenece = userServi.findById(idUsuario);
		if (aEditar != null) {
			model.addAttribute("invent", aEditar);
			model.addAttribute("usuario", pertenece);
			return "admin/editarInvent";
		} else {
			return "redirect:/admin/detalleUsuario/" + idUsuario;
		}
	}

	/**
	 * Método que maneja la petición POST de edición de inventarios.
	 * 
	 * @param i  Inventario a editar
	 * @param id ID del usuario al que pertenece el inventario. Se utiliza para
	 *           redirigir al admin a la página de detalles del usuario.
	 * @return Devuelve al admin a la página de detalles del usuario.
	 */
	@PostMapping("/editarInventario/submit/{id}")
	public String procesarEdicion(@ModelAttribute("invent") Invent i, @PathVariable("id") long id) {
		i.setUsuario(userServi.findById(id));
		invServi.edit(i);
		return "redirect:/admin/detalleUsuario/" + id;
	}

}
