/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author amarquez
 *
 */

@Data @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String nickname;
	private String password;
	private String telefono;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="usuario")
	private List<Invent> invents;
	
	public void addInvent(Invent i) {
		this.invents.add(i);
		i.setUsuario(this);
	}
	
	public void removeInvent(Invent i) {
		this.invents.remove(i);
		i.setUsuario(null);
	}
	
	/**
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param nickname
	 * @param password
	 * @param telefono
	 */
	public Usuario(String nombre, String apellidos, String email, String nickname, String password, String telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.telefono = telefono;
	}

	public Usuario(long id, String nombre, String apellidos, String email, String nickname, String password,
			String telefono, List<Invent> invents) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.telefono = telefono;
		this.invents = invents;
	}
	
	
	
	
	
	
	
	
	

}
