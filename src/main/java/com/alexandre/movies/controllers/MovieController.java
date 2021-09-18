package com.alexandre.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
	public ResponseEntity<List<Movie>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Movie> save(@RequestBody Movie movie) {
		service.save(movie);
		return ResponseEntity.ok(movie);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Movie> update(@PathVariable("id") Long id, @RequestBody Movie movie) {
		service.update(id, movie);
		return ResponseEntity.ok(movie);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public BodyBuilder remove(@PathVariable("id") Long id) {
		service.remove(id);
		return ResponseEntity.ok();
	}

}
