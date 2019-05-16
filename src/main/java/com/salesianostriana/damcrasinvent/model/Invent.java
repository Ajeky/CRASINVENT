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
public class Invent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy="invent")
	private List<Conceptos> conceptos;
	
	//Métodos Helper
	
	public void addConcepto(Conceptos c) {
		this.conceptos.add(c);
	}
	
	public void removeConcepto(Conceptos c) {
		this.conceptos.remove(c);
		c.setInvent(null);
	}
	
	
	public Invent(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Invent(long id, String nombre, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
	}	

}
