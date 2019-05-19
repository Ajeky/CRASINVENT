/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import org.springframework.stereotype.Service;

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

}
