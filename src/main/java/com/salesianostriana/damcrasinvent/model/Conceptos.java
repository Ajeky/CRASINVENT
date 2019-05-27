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
 * Clase pojo del objeto Conceptos. Cada concepto será una tabla dentro de un
 * inventario o base de datos
 * {@link com.salesianostriana.damcrasinvent.model.Invent}. Las columnas de la
 * tabla se especifican en la clase Campos
 * {@link com.salesianostriana.damcrasinvent.model.Campos}, y los valores de
 * cada campo en la clase ValoresCampos
 * {@link com.salesianostriana.damcrasinvent.model.ValoresCampos}
 * 
 * @author Álvaro Márquez
 *
 */

@Data
@NoArgsConstructor
@Entity
public class Conceptos {

	/**
	 * ID que identifica cada Concepto en la base de datos. Se autogenera con una
	 * secuencia.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Nombre de la tabla
	 */
	private String nombre;

	/**
	 * Lista de las columnas que contiene la tabla
	 */
	@OneToMany(mappedBy = "concepto")
	private List<Campos> campos;

	// Métodos Helper

	public void addCampo(Campos c) {
		this.campos.add(c);
	}

	public void removeCampo(Campos c) {
		this.campos.remove(c);
		c.setConcepto(null);
	}

	/**
	 * Lista de los valores que contienen las columnas de la tabla.
	 */
	@OneToMany(mappedBy = "concepto")
	private List<ValoresCampos> valoresCampos;

	// Métodos Helper

	public void addValorCampo(ValoresCampos v) {
		this.valoresCampos.add(v);
	}

	public void removeValorCampo(ValoresCampos v) {
		this.valoresCampos.remove(v);
		v.setConcepto(null);
	}

	/**
	 * Inventario al que pertenece la tabla
	 */
	@ManyToOne
	private Invent invent;

}
