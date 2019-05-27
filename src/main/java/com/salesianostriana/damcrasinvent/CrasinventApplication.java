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
			List<Usuario> usuarios = servicio.findAll();
			
			for (Usuario u : usuarios) {
				u.setPassword(passwordEncoder.encode(u.getPassword()));
				servicio.add(u);
			}
		};
	}

}
