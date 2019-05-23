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
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@Entity
public class Conceptos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@OneToMany(mappedBy="concepto")
	private List<Campos> campos;
	
	//Métodos Helper
	
	public void addCampo(Campos c) {
		this.campos.add(c);
	}
	
	public void removeCampo(Campos c) {
		this.campos.remove(c);
		c.setConcepto(null);
	}
	
	@OneToMany(mappedBy="concepto")
	private List<ValoresCampos> valoresCampos;
	
	//Métodos Helper
	
	public void addValorCampo(ValoresCampos v) {
		this.valoresCampos.add(v);
	}
	
	public void removeValorCampo(ValoresCampos v) {
		this.valoresCampos.remove(v);
		v.setConcepto(null);
	}
	
	@ManyToOne
	private Invent invent;

}
