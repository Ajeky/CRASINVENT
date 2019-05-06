/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author amarquez
 *
 */

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

	/**
	 * 
	 */
	public Usuario() {
		super();
	}
	
	
	
	
	

}
