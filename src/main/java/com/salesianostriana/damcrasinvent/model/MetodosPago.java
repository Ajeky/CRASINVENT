/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author amarquez
 *
 */

@Data
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
