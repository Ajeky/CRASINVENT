/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Calse pojo del objeto ValoresCampos. Cada ValorCampo será un valor de una
 * columna {@linkcom.salesianostriana.damcrasinvent.model.Campos} en el
 * inventario o la base de datos
 * {@link com.salesianostriana.damcrasinvent.model.Invent}. La tabla a la que
 * pertenece el valor se especifica en la clase Conceptos
 * {@link com.salesianostriana.damcrasinvent.model.Conceptos}
 * 
 * @author Álvaro Márquez
 *
 */

@Data
@NoArgsConstructor
@Entity
public class ValoresCampos {

	/**
	 * ID que identifica cada valor individualmente. Se autogenera con una secuencia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Valor que guarda el objeto
	 */
	private String valor;

	/**
	 * Tabla a la que pertenece el valor
	 */
	@ManyToOne
	Conceptos concepto;

	/**
	 * Columna a la que pertenece el valor
	 */
	@ManyToOne
	Campos campo;

}
