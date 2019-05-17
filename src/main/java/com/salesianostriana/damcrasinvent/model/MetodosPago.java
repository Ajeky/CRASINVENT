/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MetodosPago {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy="metodosPago")
	private List<UsuarioEmpresa> usuarios = new ArrayList<>();

	
	
	
	

}
