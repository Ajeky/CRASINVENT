/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase pojo del objeto Invent. Cada Invent será un inventario o base de datos.
 * Las tablas del inventario se especifican en la clase Conceptos
 * {@link com.salesianostriana.damcrasinvent.model.Conceptos}, las columnas de
 * cada tabla en la clase Campos
 * {@link com.salesianostriana.damcrasinvent.model.Campos}, y los valores de
 * cada columna en la clase ValoresCampos
 * {@link com.salesianostriana.damcrasinvent.model.ValoresCampos}
 * 
 * @author Álvaro Márquez
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Invent {

	/**
	 * ID que identifica a cada inventario o base de datos individualmente. Se
	 * autogenera con una secuencia.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Nombre del inventario
	 */
	private String nombre;

	/**
	 * Usuario al que pertenece el inventario
	 */
	@ManyToOne
	private Usuario usuario;

	/**
	 * Tablas que contiene la base de datos
	 */
	@OneToMany(mappedBy = "invent")
	private List<Conceptos> conceptos;

	// Métodos Helper

	public void addConcepto(Conceptos c) {
		this.conceptos.add(c);
	}

	public void removeConcepto(Conceptos c) {
		this.conceptos.remove(c);
		c.setInvent(null);
	}

	/**
	 * Constructor poco utilizado. Mayormente para pruebas.
	 */
	public Invent(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Constructor poco utilizado. Mayormente para pruebas.
	 */
	public Invent(long id, String nombre, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
	}

}
