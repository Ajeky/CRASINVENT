package com.salesianostriana.damcrasinvent;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.damcrasinvent.model.Usuario;
import com.salesianostriana.damcrasinvent.servicios.UsuarioServicio;

@SpringBootApplication
public class CrasinventApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrasinventApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UsuarioServicio servicio, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			Usuario diego = new Usuario();
			diego.setApellidos("Gonzalez Mendoza");
			diego.setEmail("diego@diego.com");
			diego.setNickname("SoulBeats");
			diego.setNombre("Diego");
			diego.setPassword("1234");
			diego.setTelefono("666666666");
			diego.setAdmin(false);
			
			Usuario alvaro = new Usuario();
			alvaro.setApellidos("Marquez Mata");
			alvaro.setEmail("alvaro@alvaro.com");
			alvaro.setNickname("Ajeky");
			alvaro.setNombre("Alvaro");
			alvaro.setPassword("1234");
			alvaro.setTelefono("123412341");
			alvaro.setAdmin(true);
			
			servicio.add(diego);
			servicio.add(alvaro);
			
			List<Usuario> usuarios = servicio.findAll();
			
			for (Usuario u : usuarios) {
				u.setPassword(passwordEncoder.encode(u.getPassword()));
				servicio.add(u);
			}
		};
	}

}
