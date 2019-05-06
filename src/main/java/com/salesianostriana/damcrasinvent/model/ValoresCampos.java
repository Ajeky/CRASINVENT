/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author amarquez
 *
 */

@Entity
public class ValoresCampos {
	
	@EmbeddedId
	private ValoresCamposPK pk;
	
	private String valor;

}
