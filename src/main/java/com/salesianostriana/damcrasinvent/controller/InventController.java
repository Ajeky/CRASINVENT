
/**
 * 
 */
package com.salesianostriana.damcrasinvent.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.damcrasinvent.formbeans.SearchBean;
import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.model.Pager;
import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.model.ValoresCampos;
import com.salesianostriana.damcrasinvent.servicios.CamposServicio;
import com.salesianostriana.damcrasinvent.servicios.ConceptosServicio;
import com.salesianostriana.damcrasinvent.servicios.InventServicio;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;
import com.salesianostriana.damcrasinvent.servicios.ValoresCamposServicio;

/**
 * Clase que controla las peticiones GET y POST que tienen que ver con objetos
 * Invent {@link com.salesianostriana.damcrasinvent.model.Invent}. Los atributos
 * de la clase son los servicios necesarios para gestionar las peticiones que se
 * realizan.
 * 
 * @author Álvaro Márquez
 *
 */

@Controller
public class InventController {

	private InventServicio inventservicio;
	private UsuarioServicio usuarioservicio;
	private ConceptosServicio concepservi;
	private CamposServicio campservi;
	private ValoresCamposServicio valorservi;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	public InventController(InventServicio servicio, UsuarioServicio usuarioservicio, ConceptosServicio concepservi,
			CamposServicio campservi, ValoresCamposServicio valorservi) {
		this.inventservicio = servicio;
		this.usuarioservicio = usuarioservicio;
		this.concepservi = concepservi;
		this.campservi = campservi;
		this.valorservi = valorservi;
	}

	/**
	 * Método que gestiona las peticiones de recibir la lista de inventarios. Se
	 * sustituyó a mitad de desarrollo por otro método que sólo muestra los
	 * inventarios que pertenezcan al usuario que realiza la petición
	 * 
	 * @deprecated
	 * @param model Como modelo se le pasa la lista de inventarios a mostrar
	 * @return La plantilla de listar inventarios
	 */
	@GetMapping({ "/inventList" })
	public String listarTodo(Model model) {
		model.addAttribute("lista", inventservicio.findAll());
		return "listas/listaInvent";
	}

	/**
	 * Método que controla la petición GET del formulario de creación de
	 * Inventarios.
	 * 
	 * @param model Se le pasan como modelos un inventario vacío al que se le
	 *              introducirán los datos en el formulario y y el Usuario al que
	 *              pertenece dicho Inventario
	 * @param id    ID del usuario al que pertenece el inventario
	 * @return La plantilla de creación de Inventario
	 */
	@GetMapping("/newInvent/{id}")
	public String mostrarFormulario(Model model, @PathVariable("id") long id, HttpSession session,
			HttpServletRequest request, ModelMap modelMap) {
		Usuario u = usuarioservicio.findById(id);

		if (request.isUserInRole("ROLE_USER") && !u.getInvents().isEmpty()) {
			return "limiteInvents";
		} else {

			model.addAttribute("usuario", u);
			model.addAttribute("invent", new Invent());
			return "forms/crearInvent";
		}
	}

	/**
	 * Método que controla la petición POST de creación de inventarios. Dependiendo
	 * del rol del usuario lo devuelve a una dirección u otra
	 * 
	 * @param i  Inventario a crear
	 * @param id ID del usuario al que pertenece el inventario
	 * @return Página en la que estaba el usuario al acceder al formulario
	 */
	@PostMapping("/newInvent/submit/{id}")
	public String procesarFormulario(@ModelAttribute("invent") Invent i, HttpSession session,
			HttpServletRequest request, ModelMap modelMap, @PathVariable("id") long id) {

		i.setUsuario(usuarioservicio.findById(id));
		inventservicio.add(i);
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/detalleUsuario/" + id;
		} else if (request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_PREMIUMUSER")) {
			return "redirect:/user/inventList";
		} else {
			return "redirect:/acceso-denegado";
		}
	}

	/**
	 * Método que gestiona la petición GET del formulario de edición del Inventario.
	 * 
	 * @param id    ID del inventario a editar
	 * @param model Como modelo se pasa el inventario a editar
	 * @return Si el inventario es nulo devuelve al usuario a la lista de
	 *         inventarios, si no, devuelve la plantilla de creación del inventario
	 */
	@GetMapping("/editInvent/{id}")
	public String editarPorID(@PathVariable("id") long id, Model model) {

		Invent invEdit = inventservicio.findById(id);
		Usuario usuario = invEdit.getUsuario();

		if (invEdit != null) {
			model.addAttribute("invent", invEdit);
			model.addAttribute("usuario", usuario);
			return "forms/crearInvent";
		} else {
			return "redirect:/user/inventList";
		}
	}

	/**
	 * Método que gestiona la petición POST de la edición de inventarios
	 * 
	 * @param i         inventario a editar
	 * @param principal Objeto que devuelve el usuario que ha realizado la petición
	 * @return La lista de inventarios del usuario
	 */
	@PostMapping("/editInvent/submit")
	public String procesarEdicion(@ModelAttribute("invent") Invent i, Principal principal) {
		i.setUsuario(usuarioservicio.buscarPorEmail(principal.getName()));
		inventservicio.edit(i);
		return "redirect:/user/inventList";
	}

	/**
	 * Método que maneja la plantilla de los detalles de un inventario. En ella se
	 * ve el nombre del inventario y los conceptos que contiene. Se pueden añadir
	 * conceptos nuevos.
	 * 
	 * @param id    ID del invent a mostrar.
	 * @param model Como modelo se pasan el invent a mostrar y una lista con los
	 *              conceptos que contiene
	 * @return Redirige al usuario a la página de detalles del inventario, o, en
	 *         caso de que se pase un inventario nulo, a la lista de inventarios.
	 */
	@GetMapping("/detalleInvent/{id}")
	public String detalleInventario(@PathVariable("id") long id, Model model) {
		Invent i = inventservicio.findById(id);
		List<Conceptos> c = i.getConceptos();
		Usuario u = i.getUsuario();

		if (i != null) {
			model.addAttribute("invent", i);
			model.addAttribute("conceptos", c);
			model.addAttribute("usuario", u);
			return "listas/inventarioDetalle";
		} else {
			return "redirect:/user/inventList";
		}
	}

	/**
	 * Método que gestiona la petición de borrar un inventario. Borra todos los
	 * conceptos, campos y valores que estuvieran relacionados con el inventario en
	 * cuestión
	 * 
	 * @param id ID del inventario a borrar
	 * @return Dependiendo del rol del usuario lo redirige a la lista de inventarios
	 *         si es un usuario o al detalle del usuario al que pertenecía el
	 *         inventario borrado si es un admin
	 */
	@GetMapping("/deleteInvent/{id}")
	public String borrar(@PathVariable("id") long id, HttpSession session, HttpServletRequest request,
			ModelMap modelMap) {
		Invent invent = inventservicio.findById(id);
		long idUsuario = invent.getUsuario().getId();

		for (Conceptos concepto : invent.getConceptos()) {
			for (Campos campo : concepto.getCampos()) {
				for (ValoresCampos valor : campo.getValoresCampos()) {
					valorservi.delete(valor);
				}
				campservi.delete(campo);
			}
			concepservi.delete(concepto);
		}

		inventservicio.delete(invent);

		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/detalleUsuario/" + idUsuario;
		} else {
			return "redirect:/user/inventList";
		}
	}

	/**
	 * Método que gestiona la plantilla de lista de inventarios que pertenecen a un
	 * usuario
	 * 
	 * @param model     como métodos se pasan un motor de búsqueda (inservible por
	 *                  ahora), el usuario al que pertenecen los inventarios y la
	 *                  lista de inventarios a mostrar
	 * @param principal Objeto que devuelve los datos de login del usuario que
	 *                  realiza la petición
	 * @return La plantilla de la lista de inventarios
	 */
	/*
	 * @GetMapping("/user/inventList") public String mostrarInventsUsuario(Model
	 * model, Principal principal) { model.addAttribute("searchForm", new
	 * SearchBean());
	 * 
	 * Usuario u = usuarioservicio.buscarPorEmail(principal.getName());
	 * 
	 * List<Invent> inventList =
	 * inventservicio.encontrarInventariosDeUnUsuario(u.getId());
	 * 
	 * model.addAttribute("usuario", u); model.addAttribute("lista", inventList);
	 * return "/listas/listaInvent"; }
	 */

	/**
	 * Método que maneja la búsqueda de inventarios. Está en desarrollo, por lo que
	 * por ahora es inservible
	 */
	@PostMapping("/search")
	public String searchProducto(@ModelAttribute("searchForm") SearchBean searchBean, Model model) {

		model.addAttribute("invent", new Invent());
		model.addAttribute("lista", inventservicio.findByNombre(searchBean.getSearch()));

		return "/listas/listaInvent";
	}

	/**
	 * 
	 * @param pageSize
	 * @param page
	 * @param nombre
	 * @param model
	 * @param principal
	 * @return
	 */
	@GetMapping({ "/inventsbuscados", "/user/inventList" })
	public String mostrarInventsUsuario(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre, Model model,
			Principal principal) {

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		String evalNombre = nombre.orElse(null);

		Page<Invent> invents = null;

		Usuario u = usuarioservicio.buscarPorEmail(principal.getName());

		// UserDetails u = (UserDetails) principal;

		if (evalNombre == null) {
			invents = inventservicio.findByUsuarioPageable(u, PageRequest.of(evalPage, evalPageSize));
		} else {
			invents = inventservicio.findByUsuarioAndNombreIgnoreCasePageable(u, evalNombre,
					PageRequest.of(evalPage, evalPageSize));
		}

		// Obtenemos la página definida por evalPage y evalPageSize de objetos de
		// nuestro modelo
		// Page<Producto> products =
		// productService.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		// Creamos el objeto Pager (paginador) indicando los valores correspondientes.
		// Este sirve para que la plantilla sepa cuantas páginas hay en total, cuantos
		// botones
		// debe mostrar y cuál es el número de objetos a dibujar.
		Pager pager = new Pager(invents.getTotalPages(), invents.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("usuario", u);
		model.addAttribute("lista", invents);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

		return "listas/listaInvent";
	}
	
	@GetMapping("/imprimirInvent/{id}")
	public String imprimirInvent(@PathVariable("id") long id, Model model) {
		Invent i = inventservicio.findById(id);
		
		int n = i.getConceptos().size();
		
		return "listas/imprimirInvent";
	}

}
