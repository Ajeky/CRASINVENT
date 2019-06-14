/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.repository.UsuarioRepository;
import com.salesianostriana.damcrasinvent.servicios.base.BaseService;

/**
 * @author amarquez
 *
 */

@Service
public class UsuarioServicio extends BaseService<Usuario, Long, UsuarioRepository> {

	public Usuario buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	
	public Usuario usuarioLogeado(Model model, Principal principal) {
		if (principal != null) {
			String email = principal.getName();
			Usuario u = buscarPorEmail(email);
			
			model.addAttribute("logeado", u);
			return u;
		}
		else {
			return null;
		}
	}
	

	public Page<Usuario> findAllPageable(Pageable pageable) {
		return repositorio.findAll(pageable);
	}
	
	public List<Usuario> findByEmail(String email) {
		return repositorio.findByEmailContainingIgnoreCase(email);
	}
	
	public Page<Usuario> findByEmailPageable(String nombre, Pageable pageable) {
		return repositorio.findByEmailContainingIgnoreCase(nombre, pageable);
	}

}
