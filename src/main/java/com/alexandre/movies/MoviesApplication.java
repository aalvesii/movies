package com.alexandre.movies;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.repositories.MovieRepository;
import com.alexandre.movies.security.entities.Usuario;
import com.alexandre.movies.security.enums.PerfilEnum;
import com.alexandre.movies.security.repositories.UsuarioRepository;
import com.alexandre.movies.utils.PasswordUtil;

@SpringBootApplication
public class MoviesApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			Usuario usuario = new Usuario();
			usuario.setEmail("usuario@email.com");
			usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
			usuario.setSenha(PasswordUtil.generateBCrypt("123456"));
			this.usuarioRepository.save(usuario);

			Usuario admin = new Usuario();
			admin.setEmail("admin@email.com");
			admin.setPerfil(PerfilEnum.ROLE_ADMIN);
			admin.setSenha(PasswordUtil.generateBCrypt("123456"));
			this.usuarioRepository.save(admin);

			Movie m1 = new Movie();
			m1.setTitle("The Lord of the Rings");
			m1.setRelease‌(LocalDate.of(2002, 1, 1));
			m1.setGenre("Fantasy/Adventure");
			m1.setOverview(
					"A young hobbit, Frodo, who has found the One Ring that belongs to the Dark Lord Sauron, begins his journey with eight companions to Mount Doom, the only place where it can be destroyed.");
			m1.setRarating(5);

			Movie m2 = new Movie();
			m2.setTitle("Fight Club");
			m2.setRelease‌(LocalDate.of(1999, 10, 27));
			m2.setGenre("Thriller/Drama");
			m2.setOverview(
					"Discontented with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. The project soon spirals down into something sinister.");
			m2.setRarating(4);

			Movie m3 = new Movie();
			m3.setTitle("Django Unchained");
			m3.setRelease‌(LocalDate.of(2013, 1, 18));
			m3.setGenre("Western/Action");
			m3.setOverview(
					"When Django, a slave, is freed, he joins forces with a bounty hunter to rescue his wife, who has been enslaved by Calvin, a hard-hearted plantation owner.");
			m3.setRarating(5);

			movieRepository.save(m1);
			movieRepository.save(m2);
			movieRepository.save(m3);

		};
	}

}
