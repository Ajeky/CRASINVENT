/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.repository.InventRepository;

/**
 * @author amarquez
 *
 */

@Service
public class InventServicio {

	private InventRepository inventrepository;

	/**
	 * @param inventrepository
	 */
	public InventServicio(InventRepository inventrepository) {
		this.inventrepository = inventrepository;
	}

	public Invent add(Invent i) {
		return inventrepository.save(i);
	}

	public Invent edit(Invent i) {
		return inventrepository.save(i);
	}

	public void delete(Invent i) {
		inventrepository.delete(i);
	}

	public List<Invent> findAll() {
		return inventrepository.findAll();
	}

	public Invent findById(long id) {
		return inventrepository.findById(id).orElse(null);
	}
	
	public Invent findByName(String name) {
		return inventrepository.findByName(name);
		
	}

}
