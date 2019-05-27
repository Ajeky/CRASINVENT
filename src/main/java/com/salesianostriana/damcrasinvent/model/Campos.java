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
 * Clase pojo del objeto Campos. Cada campo será una columna de una tabla
 * {@link com.salesianostriana.damcrasinvent.model.Conceptos} de una base de
 * datos o inventario {@link com.salesianostriana.damcrasinvent.model.Invent}.
 * Los valores de las columnas se especifican en la clase Valores Campos
 * {@link com.salesianostriana.damcrasinvent.model.ValoresCampos}
 * 
 * @author Álvaro Márquez
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Campos {

	/**
	 * ID que identifica a cada Campo en la base de datos. Se autogenera con una
	 * secuencia.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Nombre de la columna
	 */
	private String nombre;

	/**
	 * Tipo de dato que tendrán los valores de la columna
	 */
	private String tipo;

	/**
	 * Tabla a la que pertenece la columna
	 */
	@ManyToOne
	private Conceptos concepto;

	/**
	 * Lista de valores que contiene la columna
	 */
	@OneToMany(mappedBy = "campo")
	private List<ValoresCampos> valoresCampos;

	// Métodos Helper

	public void addValorCampo(ValoresCampos v) {
		this.valoresCampos.add(v);
	}

	public void removeValorCampo(ValoresCampos v) {
		this.valoresCampos.remove(v);
		v.setCampo(null);
	}

}
