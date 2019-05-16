/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@MappedSuperclass
public abstract class MetodosPago {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy="metodosPago")
	private List<UsuarioEmpresa> usuarios = new ArrayList<>();

	
	public MetodosPago(List<UsuarioEmpresa> usuarios) {
		super();
		this.usuarios = usuarios;
	}
	
	
	
	

}
