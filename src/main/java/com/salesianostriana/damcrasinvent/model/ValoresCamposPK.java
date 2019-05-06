/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author amarquez
 *
 */

@Embeddable
public class ValoresCamposPK implements Serializable {
	
	private long id_concepto;
	private long id_campo;

}
