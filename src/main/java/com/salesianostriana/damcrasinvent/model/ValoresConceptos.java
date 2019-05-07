/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@Entity
public class ValoresConceptos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long id_concepto;

}
