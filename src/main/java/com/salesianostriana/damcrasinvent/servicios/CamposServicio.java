/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.util.List;

import com.salesianostriana.damcrasinvent.model.Campos;
import com.salesianostriana.damcrasinvent.repository.CamposRepository;

/**
 * @author amarquez
 *
 */
public class CamposServicio {
	
	private CamposRepository camposrepository;

	/**
	 * @param camposrepository
	 */
	public CamposServicio(CamposRepository camposrepository) {
		this.camposrepository = camposrepository;
	}
	
	public Campos add(Campos c) {
		return camposrepository.save(c);
	}
	
	public Campos edit(Campos c) {
		return camposrepository.save(c);
	}
	
	public void delete(Campos c) {
		camposrepository.delete(c);
	}
	
	public List<Campos> findAll() {
		return camposrepository.findAll();
	}
	
	public Campos findById(long id) {
		return camposrepository.findById(id).orElse(null);
	}
	

}
