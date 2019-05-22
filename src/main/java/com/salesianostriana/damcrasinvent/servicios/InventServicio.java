/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.repository.InventRepository;
import com.salesianostriana.damcrasinvent.servicios.base.BaseService;

/**
 * @author amarquez
 *
 */

@Service
public class InventServicio extends BaseService<Invent, Long, InventRepository>{
	
	public List<Invent> encontrarInventariosDeUnUsuario(long id) {
		
		List<Invent> todas = repositorio.findAll();
		List<Invent> i = new ArrayList<Invent>();
		
		for (Invent invent : todas) {
			if (invent.getUsuario().getId() == id) {
				i.add(invent);
			}
		}
		
		return i;
	}
	
	public List<Invent> findByNombre(String nombre){
		return repositorio.findByNombreContainingIgnoreCase(nombre);
	}
	

}
