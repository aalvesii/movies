package com.alexandre.movies.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.movies.dtos.MovieDto;
import com.alexandre.movies.exceptions.MovieException;
import com.alexandre.movies.services.MovieService;

/**
 * REST API for CRUD operations on Movies
 * 
 * @author alexandre
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService service;

	/**
	 * Get all Movies
	 * 
	 * @return list of all Movies
	 */
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
	public ResponseEntity<List<MovieDto>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	/**
	 * Save the Movie
	 * 
	 * @param movie the Movie to be saved
	 * @return the saved Movie
	 * @throws MovieException
	 */
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<MovieDto> save(@Valid @RequestBody MovieDto movie, BindingResult result)
			throws MovieException {
		if (result.hasErrors()) {
			throw new MovieException(result);
		}

		return ResponseEntity.ok(service.save(movie));
	}

	/**
	 * Update a existing Movie. The undefined attributes will be ignored
	 * 
	 * @param id    the Movie id to be updated
	 * @param movie the Movie to be updated
	 * @return the updated Movie
	 */
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<MovieDto> update(@PathVariable("id") Long id, @RequestBody MovieDto movie) {
		return ResponseEntity.ok(service.update(id, movie));
	}

	/**
	 * Removes a Movie
	 * 
	 * @param id the Movie id to be deleted
	 * @return the REST status
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public BodyBuilder remove(@PathVariable("id") Long id) {
		service.remove(id);
		return ResponseEntity.ok();
	}

}
