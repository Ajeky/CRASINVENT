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
public class ValoresConceptos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long id_concepto;
	
	@ManyToOne
	Conceptos concepto;
	
	@OneToMany(mappedBy="valorConcepto")
	private List<ValoresCampos> valoresCampos;
	
	//Métodos Helper
	
	public void addValorCampo(ValoresCampos v) {
		this.valoresCampos.add(v);
	}
	
	public void removeValorCampo(ValoresCampos v) {
		this.valoresCampos.remove(v);
		v.setValorConcepto(null);
	}

}
