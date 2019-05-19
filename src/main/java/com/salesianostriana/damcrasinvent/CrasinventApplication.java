package com.salesianostriana.damcrasinvent;

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
			diego.setPassword(passwordEncoder.encode("1234"));
			diego.setTelefono("666666666");
			
			servicio.add(diego);
		};
	}

}
