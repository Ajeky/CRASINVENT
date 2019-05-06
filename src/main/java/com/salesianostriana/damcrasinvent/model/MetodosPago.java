/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author amarquez
 *
 */
@Entity
public class MetodosPago {
	
	@Id
	private String CIF;

	/**
	 * @param cIF
	 */
	public MetodosPago(String cIF) {
		CIF = cIF;
	}

	/**
	 * 
	 */
	public MetodosPago() {
	}
	
	
	
	

}
