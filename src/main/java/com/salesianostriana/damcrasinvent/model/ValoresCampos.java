/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@Entity
public class ValoresCampos {
	
	@EmbeddedId
	private ValoresCamposPK pk;
	
	private String valor;
	
	@ManyToOne
	Campos campo;
	
	@ManyToOne
	ValoresConceptos valorConcepto;
	

}
