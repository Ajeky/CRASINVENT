/**
 * 
 */
package com.salesianostriana.damcrasinvent.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author amarquez
 *
 */

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5112392424025862905L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String apellidos;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String nickname;
	private String password;
	private String telefono;
	private boolean admin;

	private boolean cuentaCaducada;
	private boolean cuentaBloqueada;
	private boolean credencialesCaducadas;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "usuario")
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
	public Usuario(String nombre, String apellidos, String email, String nickname, String password, String telefono,
			boolean admin) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.telefono = telefono;
		this.admin = admin;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (admin) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !cuentaCaducada;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !cuentaBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credencialesCaducadas;
	}

	@Override
	public boolean isEnabled() {
		return !cuentaBloqueada;
	}

}
