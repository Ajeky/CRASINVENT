/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.damcrasinvent.model.Conceptos;
import com.salesianostriana.damcrasinvent.repository.ConceptosRepository;

/**
 * @author amarquez
 *
 */

@Service
public class ConceptosServicio {

	private ConceptosRepository conceptosrepository;

	/**
	 * @param conceptosrepository
	 */
	public ConceptosServicio(ConceptosRepository conceptosrepository) {
		this.conceptosrepository = conceptosrepository;
	}

	public Conceptos add(Conceptos c) {
		return conceptosrepository.save(c);
	}

	public Conceptos edit(Conceptos c) {
		return conceptosrepository.save(c);
	}

	public void delete(Conceptos c) {
		conceptosrepository.delete(c);
	}

	public List<Conceptos> findAll() {
		return conceptosrepository.findAll();
	}

	public Conceptos findById(long id) {
		return conceptosrepository.findById(id).orElse(null);
	}

}
